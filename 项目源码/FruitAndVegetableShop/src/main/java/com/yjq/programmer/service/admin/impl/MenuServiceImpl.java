package com.yjq.programmer.service.admin.impl;

import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单service接口实现类
 * @author 82320
 *
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public ResponseVo<List<Menu>> getFirstMenus(List<Menu> allMenus) {
		//lambda + stream
		List<Menu> firstMenus = allMenus.stream().filter(e -> e.getParentId().intValue() == 0)
			.sorted(Comparator.comparing(Menu::getSort).reversed()) //排序
			.collect(Collectors.toList());
		
		return ResponseVo.success(firstMenus);
	}

	@Override
	public ResponseVo<List<Menu>> getSecondMenus(List<Menu> allMenus) {
		List<Menu> secondMenus = new ArrayList<Menu>();
		ResponseVo<List<Menu>> responseFirstMenus = getFirstMenus(allMenus);
		for(Menu firstMenu : responseFirstMenus.getData()) {
			for(Menu menu : allMenus) {
				if(menu.getParentId().equals(firstMenu.getId())) {
					secondMenus.add(menu);
				}
			}
		}
		 secondMenus = secondMenus.stream()
			.sorted(Comparator.comparing(Menu::getSort).reversed())
			.collect(Collectors.toList());
		
		return ResponseVo.success(secondMenus);
	}

	@Override
	public ResponseVo<List<Menu>> getThirdMenus(List<Menu> allMenus) {
		List<Menu> thirdMenus = new ArrayList<Menu>();
		ResponseVo<List<Menu>> responseSecondMenus = getSecondMenus(allMenus);
		for(Menu secondMenu : responseSecondMenus.getData()) {
			for(Menu menu : allMenus) {
				if(menu.getParentId().equals(secondMenu.getId())) {
					thirdMenus.add(menu);
				}
			}
		}
		thirdMenus = thirdMenus.stream()
			.sorted(Comparator.comparing(Menu::getSort).reversed())
			.collect(Collectors.toList());

		return ResponseVo.success(thirdMenus);
	}

	@Override
	public ResponseVo<Boolean> isSecondMenu(Menu menu) {
		if(menu.getParentId() != 0) {
			Menu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
			if(parentMenu.getParentId() == 0) {
				return ResponseVo.success(true);
			}else {
				return ResponseVo.success(false);
			}
		}else {
			return ResponseVo.success(false);
		}
	}

	@Override
	public ResponseVo<Boolean> isFirstMenu(Menu menu) {
		if(menu.getParentId() == 0) {
			return ResponseVo.success(true);
		}else {
			return ResponseVo.success(false);
		}
	}

	@Override
	public ResponseVo<Boolean> haveChildrenMenu(Menu menu) {
		List<Menu> allMenus = menuMapper.selectAll();
		ResponseVo<List<Menu>> thirdMenus = getThirdMenus(allMenus);
		ResponseVo<List<Menu>> secondMenus = getSecondMenus(allMenus);
		//收集符合条件三级菜单
		List<Menu> collectThirdMenus = thirdMenus.getData().stream().filter(e -> e.getParentId() == menu.getId())
				.collect(Collectors.toList());
		//收集符合条件二级菜单
		List<Menu> collectSecondMenus = secondMenus.getData().stream().filter(e -> e.getParentId() == menu.getId())
				.collect(Collectors.toList());
		//如果存在,返回true
		if(collectThirdMenus.size()>0 || collectSecondMenus.size() > 0) {
			return ResponseVo.success(true);
		}else {
			return ResponseVo.success(false);
		}
	}

	@Override
	public ResponseVo<Boolean> add(Menu menu) {
		if(menu == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//进行统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(menu);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		if(menuMapper.insertSelective(menu) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.MENU_ADD_ERROR);
		}
		return ResponseVo.successByMsg(true, "添加成功！");
	}

	@Override
	public ResponseVo<Boolean> edit(Menu menu) {
		if(menu == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		//进行统一表单验证
		CodeMsg validate = ValidateEntityUtil.validate(menu);
		if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
			return ResponseVo.errorByMsg(validate);
		}
		if(menuMapper.updateByPrimaryKeySelective(menu) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.MENU_EDIT_ERROR);
		}
		return ResponseVo.successByMsg(true, "编辑成功！");
	}

	@Override
	public ResponseVo<Boolean> delete(Integer id) {
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		if(haveChildrenMenu(selectByPrimaryKey).getData() == true) {
			return ResponseVo.errorByMsg(CodeMsg.MENU_CHILDREN_EXIST);
		}
		if(menuMapper.deleteByPrimaryKey(id) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.MENU_DELETE_ERROR);
		}
		return ResponseVo.successByMsg(true, "删除成功！");
	}

	@Override
	public ResponseVo<Integer> level(Integer id) {
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		ResponseVo<Boolean> secondMenu = isSecondMenu(selectByPrimaryKey);
		if(secondMenu.getData() == true) {
			//是二级菜单
			return ResponseVo.success(2);
		}
		return ResponseVo.success(0);
	}

	@Override
	public ResponseVo<Boolean> chageState(Integer id) {
		Menu selectedMenu = menuMapper.selectByPrimaryKey(id);
		if(selectedMenu == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		if(selectedMenu.getState().intValue() == MenuStateEnum.OPEN.getCode()) {
			//如果是开启状态,改变为停用
			selectedMenu.setState(MenuStateEnum.STOP.getCode());
			if(menuMapper.updateByPrimaryKeySelective(selectedMenu) <= 0) {
				return ResponseVo.errorByMsg(CodeMsg.MENU_STATE_CHANGE_ERROR);
			}
		}else {
			//如果是停用状态,改变为开启
			selectedMenu.setState(MenuStateEnum.OPEN.getCode());
			if(menuMapper.updateByPrimaryKeySelective(selectedMenu) <= 0) {
				return ResponseVo.errorByMsg(CodeMsg.MENU_STATE_CHANGE_ERROR);
			}
		}
		
		return ResponseVo.success(true);
	}

}
