package com.yjq.programmer.interceptor.home;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.constant.RuntimeConstant;
import com.yjq.programmer.service.home.ICartService;
import com.yjq.programmer.util.JWTUtil;
import com.yjq.programmer.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 前台用户登录拦截器
 * @author Administrator
 *
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(UserLoginInterceptor.class);

	@Autowired
	private ICartService cartService;
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestURI = request.getRequestURI();  //取得被拦截的链接，比如：requestURI=/system/index
		Boolean temp = false; //判断当前路径是否在不需要验证的路径里面  false:不在 true:在
		String token = "";
		if(requestURI.contains("/home/")){
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0){
				for (Cookie cookie : cookies) {
					if ("my_token".equals(cookie.getName())) {
						token = cookie.getValue();
					}
				}
			}
			log.info("当前URL={}", requestURI);
			log.info("当前路径获取到的token={}", token);
			try{
				//验证token
				DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
				String id = decodedJWT.getClaim("id").asString();
				request.setAttribute("id", decodedJWT.getClaim("id").asString());
				request.setAttribute("username", decodedJWT.getClaim("username").asString());
				request.setAttribute("email", decodedJWT.getClaim("email").asString());
				request.setAttribute("phone", decodedJWT.getClaim("phone").asString());
				request.setAttribute("headPic", decodedJWT.getClaim("headPic").asString());
				request.setAttribute("cartTotal", cartService.total(Long.valueOf(id)).getData()); //获取购物车商品种类
			}catch(Exception e){
				e.printStackTrace();
				//token失效或非法
				//判断该路径要不要验证
				for(String str : RuntimeConstant.userNotNeedConfirmUrl){
					if(requestURI.equals(str)){
						temp = true; //该路径不需要验证
					}
				}
				if(temp == false) {
					//该路径需要验证，并进行是否是ajax请求判断
					//首先判断是否是ajax请求
					if (StringUtil.isAjax(request)) {
						//表示是ajax请求
						try {
							response.setCharacterEncoding("UTF-8");
							//JSON.parseObject，是将Json字符串转化为相应的对象；JSON.toJSONString则是将对象转化为Json字符串。
							response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						return false;
					}
					//不是ajax请求，直接跳转页面
					try {
						log.info("没有登录或token非法，跳转登录界面！当前URL={}",requestURI);
						response.sendRedirect("/home/user/login");
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					return false;
				}
			}
		}
		return true;
	}
}
