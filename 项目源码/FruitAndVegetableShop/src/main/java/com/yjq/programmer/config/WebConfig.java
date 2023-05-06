package com.yjq.programmer.config;
/**
 * 用来配置拦截器的配置类
 */

import com.yjq.programmer.constant.RuntimeConstant;
import com.yjq.programmer.interceptor.admin.AdminLoginInterceptor;
import com.yjq.programmer.interceptor.home.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private AdminLoginInterceptor adminLoginInterceptor;

	@Autowired
	private UserLoginInterceptor userLoginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//搜寻/**所有链接除了RuntimeConstant.loginExcludePathPatterns中的链接
	    registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.adminLoginExcludePathPatterns);
		registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.userLoginExcludePathPatterns);
	}

}
