package com.yjq.programmer.util;

import com.yjq.programmer.annotion.ValidateEntity;
import com.yjq.programmer.bean.CodeMsg;

import java.lang.reflect.Field;
import java.math.BigDecimal;


/**
 * 验证实体工具类
 * @author Administrator
 *
 */
public class ValidateEntityUtil {
	
	public static CodeMsg validate(Object object){
		Field[] declaredFields = object.getClass().getDeclaredFields();  //取得对象里所有字段（不包括父字段）
		//遍历所有字段
		for(Field field : declaredFields){
			ValidateEntity annotation = field.getAnnotation(ValidateEntity.class);   //获取字段上的ValidateEntity注释
			field.setAccessible(true);    //作用就是让我们在用反射时访问私有变量
			if(annotation != null){
				if(annotation.required()){
					//表示该字段是必填字段
					try {
						Object o = field.get(object);   //把每个字段的值取出来
						//首先判断是否为空
						if(o == null){
							CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
							codeMsg.setMsg(annotation.errorRequiredMsg());
							return codeMsg;
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				//如果不是必填字段，也要对用户输入的情况进行判断
				try {
					Object o = field.get(object);   //把每个字段的值取出来
					//首先判断是不是String类型
					CodeMsg stringResult = confirmStringLength(o, annotation);
					if(stringResult.getCode().intValue() != 0) {
						return stringResult;
					}
					//其次来判断是否为数字
					CodeMsg numberResult = confirmNumberValue(o, annotation);
					if(numberResult.getCode().intValue() != 0) {
						return numberResult;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return CodeMsg.SUCCESS;
	}
	
	//如果字段是字符串类型，判断字符串长度
	public static CodeMsg confirmStringLength(Object o,ValidateEntity annotation) {
		//如果o=null,不会走下面If
		if(o instanceof String){
			//若是字符串类型，则检查其长度
			if(annotation.requiredMinLength()){
				if(o.toString().trim().length() < annotation.minLength()){
					CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
					codeMsg.setMsg(annotation.errorMinLengthMsg());
					return codeMsg;
				}
			}
			if(annotation.requiredMaxLength())
			{
				if(o.toString().trim().length() > annotation.maxLength()){
					CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
					codeMsg.setMsg(annotation.errorMaxLengthMsg());
					return codeMsg;
				}
			}
		}
		return CodeMsg.SUCCESS;
	}
	
	//如果字段是数据类型，判断其最大值和最小值是否符合
	public static CodeMsg confirmNumberValue(Object o,ValidateEntity annotation) {
		//如果o=null,不会走下面If
		if(isNumberObject(o)){
			//判断是否规定检查最小值
			if(annotation.requiredMinValue()){
				if(Double.valueOf(o.toString().trim()) < annotation.minValue()){
					CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
					codeMsg.setMsg(annotation.errorMinValueMsg());
					return codeMsg;
				}
			}
			//判断是否规定检查最大值
			if(annotation.requiredMaxValue()){
				if(Double.valueOf(o.toString().trim()) > annotation.maxValue()){
					CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
					codeMsg.setMsg(annotation.errorMaxValueMsg());
					return codeMsg;
				}
			}
		}else if(isBigDecimalObject(o)) {
			//判断是否规定检查最小值
			BigDecimal inputValue = new BigDecimal(o.toString().trim()); //用户输入的值 这种声明方法精度有保障
			if(annotation.requiredMinValue()){
				BigDecimal minValue = BigDecimal.valueOf(annotation.minValue()); //这种声明方法精度有保障
				if(inputValue.compareTo(minValue) == -1){
					CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
					codeMsg.setMsg(annotation.errorMinValueMsg());
					return codeMsg;
				}
			}
			//判断是否规定检查最大值
			if(annotation.requiredMaxValue()){
				BigDecimal maxValue = BigDecimal.valueOf(annotation.maxValue()); //这种声明方法精度有保障
				if(inputValue.compareTo(maxValue) == 1){
					CodeMsg codeMsg = CodeMsg.VALIDATE_ENTITY_ERROR;
					codeMsg.setMsg(annotation.errorMaxValueMsg());
					return codeMsg;
				}
			}
		}
		
		return CodeMsg.SUCCESS;
	}
	
	
	
	/**
	 * 检查对象是否是数字类型
	 * @param object
	 * @return
	 */
	public static boolean isNumberObject(Object object){
		if(object instanceof Integer)return true;
		if(object instanceof Long)return true;
		if(object instanceof Float)return true;
		if(object instanceof Double)return true;
		return false;
	}
	
	/**
	 * 检查对象是否是BigDecimal类型
	 * @param object
	 * @return
	 */
	public static boolean isBigDecimalObject(Object object){
		if(object instanceof BigDecimal)return true;
		return false;
	}
}
