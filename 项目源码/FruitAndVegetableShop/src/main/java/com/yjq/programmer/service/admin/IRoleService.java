package com.yjq.programmer.service.admin;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.admin.Role;
import com.yjq.programmer.vo.common.ResponseVo;

/**
 * 角色service接口
 * @author 82320
 *
 */
public interface IRoleService {
	
	
	//通过分页获取角色列表
	ResponseVo<PageInfo> getRoleListByPage(Integer pageNum, Integer pageSize);
		
	//通过分页和查询信息获取角色列表
	ResponseVo<PageInfo> getRoleListByPageAndName(Integer pageNum, Integer pageSize, String name);
	
	//添加角色
	ResponseVo<Boolean> add(Role role);
	
	//编辑角色
	ResponseVo<Boolean> edit(Role role);
	
	//角色删除
	ResponseVo<Boolean> delete(Integer id);
	
	//判断角色名称是否有重名
	ResponseVo<Boolean> isNameExist(Role role, Integer id);
	
	//保存角色权限
	ResponseVo<Boolean> saveAuthority(String ids, Integer roleId);
}
