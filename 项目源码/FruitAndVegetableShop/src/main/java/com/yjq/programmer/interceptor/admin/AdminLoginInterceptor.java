package com.yjq.programmer.interceptor.admin;

import com.alibaba.fastjson.JSON;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.constant.SessionConstant;
import com.yjq.programmer.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 后台管理员登录拦截器
 * @author Administrator
 *
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(AdminLoginInterceptor.class);
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();  //取得被拦截的链接，比如：requestURI=/system/index
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionConstant.SESSION_ADMIN_LOGIN_KEY);
		if(attribute == null && requestURI.contains("/admin/")){
			log.info("当前URL=" + requestURI);
			//首先判断是否是ajax请求
			if(StringUtil.isAjax(request)){
				//表示是ajax请求
				try {
					response.setCharacterEncoding("UTF-8");
					//JSON.parseObject，是将Json字符串转化为相应的对象；JSON.toJSONString则是将对象转化为Json字符串。
					response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
			//说明是普通的请求，可直接重定向到登录页面
			//用户还未登录或者session失效,重定向到登录页面
			try {
				log.info("没有登录或session失效，跳转登录界面！当前URL={}" + requestURI);
				response.sendRedirect("/admin/system/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
}
