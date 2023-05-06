package com.yjq.programmer.service.admin;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.vo.common.ResponseVo;

/**
 * 管理员service接口
 * @author 82320
 *
 */
public interface IAdminService {

	//通过分页获取管理员列表
	ResponseVo<PageInfo> getAdminListByPage(Integer pageNum, Integer pageSize);
	
	//通过分页和查询信息获取管理员列表
	ResponseVo<PageInfo> getAdminListByPageAndName(Integer pageNum, Integer pageSize, String name);
	
	//判断管理员名称是否有重名
	ResponseVo<Boolean> isNameExist(Admin admin, Integer id);
	
	//添加管理员
	ResponseVo<Boolean> add(Admin admin);
	
	//编辑管理员
	ResponseVo<Admin> edit(Admin admin);
	
	//删除管理员
	ResponseVo<Boolean> delete(Integer id);
	
	//更改管理员状态
	ResponseVo<Boolean> chageState(Integer id);
}
