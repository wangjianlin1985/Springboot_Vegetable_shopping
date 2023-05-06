package com.yjq.programmer.enums;
/**
 * 后台管理系统邮件删除状态枚举类：1:双方均未删除  2：发送者删除；3：接收者删除
 * @author 82320
 *
 */
public enum MailDeleteStateEnum {

	ALL_NOT_DELETE(1,"双方均未删除"),
	
	SENDER_DELETE(2,"发送者删除"),
	
	RECEIVER_DELETE(3,"接收者删除"),
	
	;
	
	Integer code;
	
	String desc;
	
	MailDeleteStateEnum(Integer code,String desc) {
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
