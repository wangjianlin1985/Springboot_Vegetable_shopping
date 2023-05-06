package com.yjq.programmer.enums;
/**
 * 管理员状态枚举类：1：启用；2：冻结
 * @author 82320
 *
 */
public enum AdminStateEnum {

	OPEN(1,"启用"),
	
	STOP(2,"冻结"),
	
	;
	
	Integer code;
	
	String desc;
	
	AdminStateEnum(Integer code,String desc) {
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
