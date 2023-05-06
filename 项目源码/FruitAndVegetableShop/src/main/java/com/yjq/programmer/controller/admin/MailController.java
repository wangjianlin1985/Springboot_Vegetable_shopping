package com.yjq.programmer.controller.admin;

import com.github.pagehelper.util.StringUtil;
import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.*;
import com.yjq.programmer.enums.MenuStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.pojo.admin.Mail;
import com.yjq.programmer.pojo.admin.Menu;
import com.yjq.programmer.service.admin.IMailService;
import com.yjq.programmer.service.admin.IMenuService;
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
 * 后台管理系统邮箱控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/mail")
@Controller
public class MailController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private MailMapper mailMapper;
	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	/**
	 * 发送邮件页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String index(Model model,Integer id,HttpServletRequest request) {
		//获取路径上有关信息
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		List<Authority> selectByRoleId = authorityMapper.selectByRoleId(loginedAdmin.getRoleId()); //获取当前用户所有权限
		Set<Integer> menuIdSet = selectByRoleId.stream().map(Authority :: getMenuId).collect(Collectors.toSet());//把权限中所有菜单id取出来
		List<Menu> allMenusByStateAndPrimaryKeys = menuMapper.selectByStateAndPrimaryKeys(MenuStateEnum.OPEN.getCode(), menuIdSet);
		model.addAttribute("Receivers", adminMapper.selectAll()); //获取所有收信人
		model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByStateAndPrimaryKeys).getData());
		model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
		model.addAttribute("currentMenu", selectByPrimaryKey);
		return "admin/mail/write";
	}
	
	/**
	 * 收件箱页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/receive",method=RequestMethod.GET)
	public String receive(Model model,Integer id,String title,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize, //每页5个数据
			HttpServletRequest request
			) {
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		//获取列表展示有关信息
		if(StringUtil.isEmpty(title)) {
			//如果查询信息为空
			model.addAttribute("PageInfo", mailService.getReceiveMailsByPage(pageNum, pageSize, loginedAdmin.getId()).getData());
		}else {
			model.addAttribute("PageInfo", mailService.getReceiveMailsByPageAndTitle(pageNum, pageSize, loginedAdmin.getId(), title).getData());
			model.addAttribute("title",title);
		}
		
		//获取路径上有关信息
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		model.addAttribute("allAdmins", adminMapper.selectAll()); 
		List<Menu> allMenusByState = menuMapper.selectByState(MenuStateEnum.OPEN.getCode()); //获取所有状态开启的菜单
		model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByState).getData());
		model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
		model.addAttribute("currentMenu", selectByPrimaryKey);
		return "admin/mail/receive";
	}
	
	/**
	 * 发件箱页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/send",method=RequestMethod.GET)
	public String send(Model model,Integer id,String title,
			@RequestParam(required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize, //每页5个数据
			HttpServletRequest request
			) {
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		//获取列表展示有关信息
		if(StringUtil.isEmpty(title)) {
			//如果查询信息为空
			model.addAttribute("PageInfo", mailService.getSendMailsByPage(pageNum, pageSize, loginedAdmin.getId()).getData());
		}else {
			model.addAttribute("PageInfo", mailService.getSendMailsByPageAndTitle(pageNum, pageSize, loginedAdmin.getId(), title).getData());
			model.addAttribute("title",title);
		}
		
		//获取路径上有关信息
		Menu selectByPrimaryKey = menuMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		model.addAttribute("allAdmins", adminMapper.selectAll()); 
		List<Menu> allMenusByState = menuMapper.selectByState(MenuStateEnum.OPEN.getCode()); //获取所有状态开启的菜单
		model.addAttribute("onThirdMenus", menuService.getThirdMenus(allMenusByState).getData());
		model.addAttribute("parentMenu", menuMapper.selectByPrimaryKey(selectByPrimaryKey.getParentId()));
		model.addAttribute("currentMenu", selectByPrimaryKey);
		return "admin/mail/send";
	}
	
	
	/**
	 * 邮件查看
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String preview(Model model,Integer id) {
		Mail selectByPrimaryKey = mailMapper.selectByPrimaryKey(id);
		if(selectByPrimaryKey == null) {
			return "error/404";
		}
		model.addAttribute("allAdmins", adminMapper.selectAll()); 
		model.addAttribute("allAttachments", attachmentMapper.selectAll()); 
		model.addAttribute("previewMail", selectByPrimaryKey);
		return "admin/mail/preview";
	}
	
	/**
	 * 邮件附件移除
	 * @param Id
	 * @return
	 */
	@RequestMapping(value="/remove_attachment",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> removeAttachment(Integer Id){
		return mailService.removeAttachment(Id);
	}
	
	/**
	 * 发送邮件
	 * @param mail
	 * @return
	 */
	@RequestMapping(value="/send_mail",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> sendMail(Mail mail,String receivers,HttpServletRequest request){
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		return mailService.sendMail(mail, receivers, loginedAdmin.getId());
	}
	
	/**
	 * 邮件删除处理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> delete(Integer id,HttpServletRequest request){
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		return mailService.delete(id, loginedAdmin.getId());
	}
}
