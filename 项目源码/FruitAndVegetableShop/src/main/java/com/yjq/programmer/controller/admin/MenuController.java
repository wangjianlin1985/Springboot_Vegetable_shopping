package com.yjq.programmer.controller.admin;

import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 后台管理系统菜单控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {

	@Autowired
	private IMenuService menuService;
	
	@Autowired 
	private MenuMapper menuMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	
	/**
	 * 菜单列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,Integer id,HttpServletRequest request) {
		//获取列表展示有关信息
		List<Menu> allMenus = menuMapper.selectAll();
		model.addAttribute("FirstMenus",menuService.getFirstMenus(allMenus).getData());
		model.addAttribute("SecondMenus",menuService.getSecondMenus(allMenus).getData());
		model.addAttribute("ThirdMenus",menuService.getThirdMenus(allMenus).getData());
		
		//获取路径上有关信息
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
		Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
		List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
		model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
		model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
		model.addAttribute("currentMenu", selectByPrimaryKey);
		return "admin/menu/index";
	}
	
	/**
	 * 菜单添加图标页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/icon",method=RequestMethod.GET)
	public String icon(Model model) {
		return "admin/menu/icon";
	}
	
	/**
	 * 菜单添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		List<Menu> allMenus = menuMapper.selectAll();
		model.addAttribute("FirstMenus",menuService.getFirstMenus(allMenus).getData());
		return "admin/menu/add";
	}
	
	/**
	 * 菜单编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,Integer id) {
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		List<Menu> allMenus = menuMapper.selectAll();
		model.addAttribute("FirstMenus",menuService.getFirstMenus(allMenus).getData());
		model.addAttribute("SecondMenus",menuService.getSecondMenus(allMenus).getData());
		model.addAttribute("editMenu",selectByPrimaryKey);
		return "admin/menu/edit";
	}
	
	/**
	 * 菜单添加按钮页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/add_button",method=RequestMethod.GET)
	public String add_button(Model model,Integer id) {
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		model.addAttribute("secondMenu",selectByPrimaryKey);
		return "admin/menu/add_button";
	}
	
	/**
	 * 菜单添加表单处理
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> add(Menu menu){
		return menuService.add(menu);
	}
	
	/**
	 * 菜单编辑表单处理
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> edit(Menu menu){
		return menuService.edit(menu);
	}
	
	/**
	 * 菜单删除处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> delete(Integer id){
		return menuService.delete(id);
	}
	
	/**
	 * 判断当前菜单是否是二级菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/level",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Integer> level(Integer id){
		return menuService.level(id);
	}
	
	/**
	 * 菜单更改状态处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/change_state",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> chageState(Integer id){
		return menuService.chageState(id);
	}
		
}
