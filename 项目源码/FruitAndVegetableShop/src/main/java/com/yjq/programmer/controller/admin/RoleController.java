package com.yjq.programmer.controller.admin;

import com.github.pagehelper.util.StringUtil;
import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.dao.admin.MenuMapper;
import com.yjq.programmer.dao.admin.RoleMapper;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.pojo.admin.Role;
import com.yjq.programmer.service.admin.IMenuService;
import com.yjq.programmer.service.admin.impl.RoleServiceImpl;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 后台管理系统角色控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {

	@Autowired
	private IMenuService menuService;
	
	@Autowired 
	private MenuMapper menuMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	/**
	 * 角色列表页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,Integer id,String name,HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
			) {
		//获取列表展示有关信息
		if(StringUtil.isEmpty(name)) {
			//如果查询信息为空
			model.addAttribute("PageInfo", roleService.getRoleListByPage(pageNum, pageSize).getData());
		}else {
			model.addAttribute("PageInfo", roleService.getRoleListByPageAndName(pageNum, pageSize, name).getData());
			model.addAttribute("name",name);
		}
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
		return "admin/role/index";
	}
	
	/**
	 * 角色添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		return "admin/role/add";
	}
	
	/**
	 * 角色编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,Integer id) {
		Role selectByPrimaryKey = roleMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		model.addAttribute("editRole", selectByPrimaryKey);
		return "admin/role/edit";
	}
	
	
	@RequestMapping(value="/authority",method=RequestMethod.GET)
	public String authority(Model model,Integer id) {
		if(roleMapper.selectByPrimaryKey(id) == null) {
			return "error/404";
		}
		List<Menu> allMenusByState = menuMapper.selectByState(MenuStateEnum.OPEN.getCode());
		model.addAttribute("FirstMenus",menuService.getFirstMenus(allMenusByState).getData());
		model.addAttribute("SecondMenus",menuService.getSecondMenus(allMenusByState).getData());
		model.addAttribute("ThirdMenus",menuService.getThirdMenus(allMenusByState).getData());
		model.addAttribute("id", id);
		model.addAttribute("AuthorityList", authorityMapper.selectByRoleId(id));
		return "admin/role/authority";
	}
	
	/**
	 * 角色权限表单处理
	 * @param ids
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/save_authority",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> saveAuthority(String ids, Integer roleId){
		return roleService.saveAuthority(ids, roleId);
	}
	
	
	/**
	 * 角色添加表单处理
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> add(Role role){
		return roleService.add(role);
	}
	
	/**
	 * 角色编辑表单处理
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> edit(Role role){
		return roleService.edit(role);
	}
	
	/**
	 * 角色删除处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> delete(Integer id){
		return roleService.delete(id);
	}
	
	
}
