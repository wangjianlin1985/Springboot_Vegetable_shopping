package com.yjq.programmer.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author Administrator
 *
 */
public class RuntimeConstant {

	//后台管理员登录拦截器无需拦截的url      Arrays.asList：字符串数组转化为List
	public static List<String> adminLoginExcludePathPatterns = Arrays.asList(
			"/admin/system/login",
			"/common/cpacha/generate_cpacha",
			"/admin/login/**",
			"/admin/common/**",
			"/admin/X-admin-2.2/**",
			"/home/css/**",
			"/home/common/**",
			"/home/font/**",
			"/home/images/**",
			"/home/js/**",
			"/home/system/js/slider.js",
			"/ueditor/**",
			"/photo/**"
		);
	//前台用户登录拦截器无需拦截的url      Arrays.asList：字符串数组转化为List
	public static List<String> userLoginExcludePathPatterns = Arrays.asList(
			"/admin/system/login",
			"/home/system/js/slider.js",
			"/common/cpacha/generate_cpacha",
			"/admin/login/**",
			"/admin/common/**",
			"/admin/X-admin-2.2/**",
			"/home/css/**",
			"/home/common/**",
			"/home/font/**",
			"/home/images/**",
			"/home/js/**",
			"/ueditor/**",
			"/photo/**"
	);

	//前台用户访问需要拦截但无需验证的url      Arrays.asList：字符串数组转化为List
	public static List<String> userNotNeedConfirmUrl = Arrays.asList(
			"/home/system/index",
			"/home/user/login",
			"/home/user/register",
			"/home/product/fruit",
			"/home/product/vegetable",
			"/home/product/detail"
	);
}
