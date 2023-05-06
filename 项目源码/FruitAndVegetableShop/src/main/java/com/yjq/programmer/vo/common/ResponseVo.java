package com.yjq.programmer.vo.common;

import com.yjq.programmer.bean.CodeMsg;

/**
 * 公共返回类
 * @author 82320
 *
 * @param <T>
 */
public class ResponseVo<T> {
	
	private Integer code;
	
	private String msg;
	
	private T data;
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private ResponseVo(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private ResponseVo(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	private ResponseVo(Integer code, T data) {
		this.code = code;
		this.data = data;
	}
	
	public static <T> ResponseVo<T> success(T data) {
		return new ResponseVo<>(CodeMsg.SUCCESS.getCode(), data);
	}
	
	public static <T> ResponseVo<T> successByMsg(T data,String msg) {
		return new ResponseVo<>(CodeMsg.SUCCESS.getCode(), msg, data);
	}
	
	public static <T> ResponseVo<T> errorByMsg(CodeMsg codeMsg) {
		return new ResponseVo<>(codeMsg.getCode(),codeMsg.getMsg());
	}

}
