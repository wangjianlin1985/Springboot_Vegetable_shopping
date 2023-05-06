package com.yjq.programmer.enums;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-08 16:49
 */

/**
 * 前台系统邮件发送类别枚举类
 */
public enum MailTypeEnum {

    USER_REGISTER(1,"用户注册"),

    ORDER_SUBMIT(2,"订单提交"),

    ;

    Integer code;

    String desc;

    MailTypeEnum(Integer code,String desc) {
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
