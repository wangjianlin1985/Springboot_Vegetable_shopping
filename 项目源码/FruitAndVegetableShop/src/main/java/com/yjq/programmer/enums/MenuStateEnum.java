package com.yjq.programmer.enums;
/**
 * 菜单状态枚举类： 菜单状态：1:开启；2：停用
 * @author 82320
 *
 */
public enum MenuStateEnum {

	OPEN(1,"开启"),
	
	STOP(2,"关闭"),
	
	;
	
	Integer code;
	
	String desc;
	
	MenuStateEnum(Integer code,String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
