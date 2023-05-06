package com.yjq.programmer.service.admin;

import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.vo.common.ResponseVo;

import java.util.List;


/**
 * 菜单service接口
 * @author 82320
 *
 */
public interface IMenuService {

	//获取一级菜单
	ResponseVo<List<Menu>> getFirstMenus(List<Menu> allMenus);
	
	//获取二级菜单
	ResponseVo<List<Menu>> getSecondMenus(List<Menu> allMenus);
	
	//获取三级菜单
	ResponseVo<List<Menu>> getThirdMenus(List<Menu> allMenus);
	
	//判断是否是二级菜单
	ResponseVo<Boolean> isSecondMenu(Menu menu);
	
	//判断是否是一级菜单
	ResponseVo<Boolean> isFirstMenu(Menu menu);
	
	//判断某个菜单是否有子菜单
	ResponseVo<Boolean> haveChildrenMenu(Menu menu);
	
	//菜单添加
	ResponseVo<Boolean> add(Menu menu);
	
	//菜单编辑
	ResponseVo<Boolean> edit(Menu menu);
	
	//菜单删除
	ResponseVo<Boolean> delete(Integer id);
	
	//判断当前菜单是否是二级菜单
	ResponseVo<Integer> level(Integer id);
	
	//更改菜单状态
	ResponseVo<Boolean> chageState(Integer id);
}
