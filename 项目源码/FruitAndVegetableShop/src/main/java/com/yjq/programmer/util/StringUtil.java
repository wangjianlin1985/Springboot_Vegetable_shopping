package com.yjq.programmer.util;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目通用工具类
 * @author Administrator
 *
 */
public class StringUtil {
	
	
	/**
	 * 返回指定格式的日期字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String getFormatterDate(Date date,String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
	
	/**
	 * 判断请求是否是ajax
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(header))return true;
		return false;
	}
	
	/**
	 * 验证字符串是否为空
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str)) {
			return true; //为空
		}else {
			return false; //不为空
		}
	}
	
}
