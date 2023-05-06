package com.yjq.programmer.enums;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-17 10:36
 */

/**
 * 用户是否删除订单枚举类  0：未删除；1：已删除
 */
public enum OrderDeleteEnum {

    NO(0,"未删除"),

    YES(1,"已删除"),

    ;

    Integer code;

    String desc;

    OrderDeleteEnum(Integer code,String desc) {
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
