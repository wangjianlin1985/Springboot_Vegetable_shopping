package com.yjq.programmer.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AnnouncementMapper;
import com.yjq.programmer.enums.AdminStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.service.admin.IAdminService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 管理员service接口实现类
 * @author 82320
 *
 */
@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AnnouncementMapper announcementMapper;
	
	@Override
	public ResponseVo<PageInfo> getAdminListByPage(Integer pageNum, Integer pageSize) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Admin> allAdmins = adminMapper.selectAll();
		PageInfo pageInfo = new PageInfo<>(allAdmins);
		pageInfo.setList(allAdmins);
		return ResponseVo.success(pageInfo);
	}

	@Override
	public ResponseVo<Boolean> isNameExist(Admin admin, Integer id) {
		Admin selectByName = adminMapper.selectByName(admin.getName()); 
		if(selectByName != null) {
			if(!selectByName.getId().equals(id)) {
				return ResponseVo.success(true); //出现重名
			}
		}
		return ResponseVo.success(false);//没有重名
	}

	@Override
	public ResponseVo<Boolean> add(Admin admin) {
		if(admin == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		admin.setPassword("123456"); //添加时使用默认密码
		//统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(admin);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		//进行手机号长度验证
		if(admin.getMobile().toString().length() != 11) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_MOBILE_LENGTH_ERROR);
		}
		//判断管理员名称是否重名
		if(isNameExist(admin, 0).getData()) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_NAME_EXIST);
		}
		//添加数据库
		if(adminMapper.insertSelective(admin) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_ADD_ERROR);
		}
		return ResponseVo.successByMsg(true,"添加成功！");
	}

	@Override
	public ResponseVo<Admin> edit(Admin admin) {
		if(admin == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(admin);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		//进行手机号长度验证
		if(admin.getMobile().toString().length() != 11) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_MOBILE_LENGTH_ERROR);
		}
		//判断管理员名称是否重名
		if(isNameExist(admin, admin.getId()).getData()) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_NAME_EXIST);
		}
		//更新数据库
		if(adminMapper.updateByPrimaryKeySelective(admin) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_EDIT_ERROR);
		}
		
		return ResponseVo.success(admin);
	}

	@Override
	public ResponseVo<Boolean> delete(Integer id) {
		if(id == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//删除该管理员所有公告
		if(announcementMapper.deleteByAdminId(id) < 0) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_DELETE_ERROR);
		}
		if(adminMapper.deleteByPrimaryKey(id) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.ADMIN_DELETE_ERROR);
		}
		return ResponseVo.successByMsg(true, "删除成功！");
	}

	@Override
	public ResponseVo<Boolean> chageState(Integer id) {
		Admin selectedAdmin = adminMapper.selectByPrimaryKey(id);
		if(selectedAdmin == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		if(selectedAdmin.getState().intValue() == AdminStateEnum.OPEN.getCode()) {
			//如果是启用状态,改变为冻结
			selectedAdmin.setState(AdminStateEnum.STOP.getCode());
			if(adminMapper.updateByPrimaryKeySelective(selectedAdmin) <= 0) {
				return ResponseVo.errorByMsg(CodeMsg.ADMIN_STATE_CHANGE_ERROR);
			}
		}else {
			//如果是冻结状态,改变为启用
			selectedAdmin.setState(AdminStateEnum.OPEN.getCode());
			if(adminMapper.updateByPrimaryKeySelective(selectedAdmin) <= 0) {
				return ResponseVo.errorByMsg(CodeMsg.ADMIN_STATE_CHANGE_ERROR);
			}
		}
		
		return ResponseVo.success(true);
	}

	@Override
	public ResponseVo<PageInfo> getAdminListByPageAndName(Integer pageNum, Integer pageSize, String name) {
		//pageNum:页号，pageSize:每页个数（插件作用）
		//举例：页号1页个数2：取获取数据的前两条；页号2页个数2：取前面两条数据的后两条
		PageHelper.startPage(pageNum, pageSize);
		List<Admin> selectBySearchName = adminMapper.selectBySearchName(name);
		PageInfo pageInfo = new PageInfo<>(selectBySearchName);
		pageInfo.setList(selectBySearchName);
		return ResponseVo.success(pageInfo);
	}

}
