package com.yjq.programmer.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.RoleMapper;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Role;
import com.yjq.programmer.service.admin.IRoleService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * 角色service接口实现类
 * @author 82320
 *
 */
@Service 
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Override
	public ResponseVo<PageInfo> getRoleListByPage(Integer pageNum, Integer pageSize) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Role> allRoles = roleMapper.selectAll();
		PageInfo pageInfo = new PageInfo<>(allRoles);
		pageInfo.setList(allRoles);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<PageInfo> getRoleListByPageAndName(Integer pageNum, Integer pageSize, String name) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Role> selectBySearchName = roleMapper.selectBySearchName(name);
		PageInfo pageInfo = new PageInfo<>(selectBySearchName);
		pageInfo.setList(selectBySearchName);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<Boolean> add(Role role) {
		if(role == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(role);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		//判断角色名称是否有重名
		if(isNameExist(role, 0).getData()) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_NAME_EXIST);
		}
		//添加数据库
		if(roleMapper.insertSelective(role) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_ADD_ERROR);
		}
		return ResponseVo.successByMsg(true, "添加成功！");
	}

	@Override
	public ResponseVo<Boolean> edit(Role role) {
		if(role == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(role);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		//判断角色名称是否有重名
		if(isNameExist(role, role.getId()).getData()) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_NAME_EXIST);
		}
		//更新数据库
		if(roleMapper.updateByPrimaryKeySelective(role) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_EDIT_ERROR);
		}
		return ResponseVo.successByMsg(true, "编辑成功！");
	}

	@Override
	public ResponseVo<Boolean> delete(Integer id) {
		if(id == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//把所属该角色的用户角色置位0（无）
		List<Admin> selectByRoleId = adminMapper.selectByRoleId(id);
		Admin admin = new Admin();
		admin.setRoleId(0);
		Set<Integer> adminIdSet = selectByRoleId.stream().map(Admin::getId).collect(Collectors.toSet());
		if(adminIdSet.size() > 0) {
			if(adminMapper.updateByPrimaryKeySetSelective(adminIdSet, admin) <= 0) {
				return ResponseVo.errorByMsg(CodeMsg.ADMIN_ROLE_EDIT_ERROR);
			}
		}
		//把权限所属该角色的部分全部删除(不能取等，因为可能该角色已经没有权限)
		if(authorityMapper.deleteByRoleId(id) < 0) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_AUTHORITY_DELETE_ERROR);
		}
		//最后删除角色
		if(roleMapper.deleteByPrimaryKey(id) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_DELETE_ERROR);
		}
		return ResponseVo.successByMsg(true, "删除成功！");
	}

	@Override
	public ResponseVo<Boolean> isNameExist(Role role, Integer id) {
		Role selectByName = roleMapper.selectByName(role.getName()); 
		if(selectByName != null) {
			if(!selectByName.getId().equals(id)) {
				return ResponseVo.success(true); //出现重名
			}
		}
		return ResponseVo.success(false);//没有重名
	}

	@Override
	public ResponseVo<Boolean> saveAuthority(String ids, Integer roleId) {
		if(roleMapper.selectByPrimaryKey(roleId) == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//保存新权限之前，先把之前该角色所属权限删光
		if(authorityMapper.deleteByRoleId(roleId) < 0) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_AUTHORITY_DELETE_ERROR);
		}
		//如果没有权限信息，直接返回保存成功
		if(StringUtil.isEmpty(ids)) {
			return ResponseVo.successByMsg(true, "角色权限保存成功！");
		}
		List<Authority> authorityList = new ArrayList<>();
		String[] split = ids.split(",");
		for(int i=0;i<split.length;i++) {
			Authority authority = new Authority(); //每插入一个Authority，都要声明一个Authority对象，不然List会覆盖
			authority.setRoleId(roleId);
			try {
				authority.setMenuId(Integer.valueOf(split[i]));
			}catch(Exception e) {
				log.info("当前权限为空，强转异常...");
			}
			authorityList.add(authority);
		}
		//添加数据库
		if(authorityMapper.batchInsert(authorityList) < 0) {
			return ResponseVo.errorByMsg(CodeMsg.ROLE_AUTHORITY_UPDATE_ERROR);
		}
		return ResponseVo.successByMsg(true, "角色权限保存成功！");
	}

	
}
