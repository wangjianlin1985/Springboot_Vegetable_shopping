package com.yjq.programmer.controller.admin;

import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.dao.admin.AdminMapper;
import com.yjq.programmer.dao.admin.AuthorityMapper;
import com.yjq.programmer.enums.AdminStateEnum;
import com.yjq.programmer.pojo.admin.Admin;
import com.yjq.programmer.pojo.admin.Authority;
import com.yjq.programmer.util.StringUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 后台管理系统系统控制器
 * @author 82320
 *
 */
@RequestMapping("/admin/system")
@Controller
public class SystemController {

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	
	/**
	 * 系统登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model) {
		return "admin/system/login";
	}
	
	/**
	 * 个人信息页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/person_info",method=RequestMethod.GET)
	public String personInfo(Model model) {
		return "admin/system/person_info";
	}
	
	/**
	 * 退出登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request) {
		request.getSession().setAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY, null);
		return "admin/system/login";
	}
	
	/**
	 * 登录表单验证处理
	 * @param admin
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> login(Admin admin, String cpacha, HttpServletRequest request){
		if(admin == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		if(StringUtil.isEmpty(cpacha)) {
			return ResponseVo.errorByMsg(CodeMsg.CPACHA_EMPTY);
		}
		String correct_cpacha = (String) request.getSession().getAttribute("admin_login");
		//判断验证码是否过期
		if(StringUtil.isEmpty(correct_cpacha)){
			return ResponseVo.errorByMsg(CodeMsg.CPACHA_EXPIRE);
		}
		if(!cpacha.toLowerCase().equals(correct_cpacha.toLowerCase())) {
			return ResponseVo.errorByMsg(CodeMsg.CPACHA_ERROR);
		}
		//去数据库查询数据验证
		Admin selectByNameAndPassword = adminMapper.selectByNameAndPassword(admin.getName(), admin.getPassword());
		if(selectByNameAndPassword == null) {
			return ResponseVo.errorByMsg(CodeMsg.USERNAME_OR_PASSWORD_ERROR);
		}
		//验证该用户是否被冻结
		if(selectByNameAndPassword.getState().intValue() == AdminStateEnum.STOP.getCode().intValue()) {
			return ResponseVo.errorByMsg(CodeMsg.USER_STATE_ERROR);
		}
		//验证该用户是否无权限
		List<Authority> selectByRoleId = authorityMapper.selectByRoleId(selectByNameAndPassword.getRoleId());
		if(selectByRoleId == null || selectByRoleId.size() == 0) {
			return ResponseVo.errorByMsg(CodeMsg.USER_AUTHORITY_ERROR);
		}
		//创建权限
		request.getSession().setAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY, selectByNameAndPassword);
		return ResponseVo.success(true);
	}
	

	@RequestMapping(value="/save_person_info",method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo<Boolean> savePersonInfo(Admin admin,HttpServletRequest request){
		if(admin == null) {
			return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
		}
		Admin loginedAdmin = (Admin) request.getSession().getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		BeanUtils.copyProperties(admin, loginedAdmin, "id","createTime","updateTime","roleId","state");
		if(adminMapper.updateByPrimaryKeySelective(loginedAdmin) <= 0) {
			return ResponseVo.errorByMsg(CodeMsg.PERSON_INFO_SAVE_ERROR);
		}
		//更新权限
		request.getSession().setAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY, loginedAdmin);
		return ResponseVo.successByMsg(true, "保存个人信息成功！");
	}
	
}
