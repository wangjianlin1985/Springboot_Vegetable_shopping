package com.yjq.programmer.enums;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-11 14:10
 */

/**
 * 地址是否是首选地址枚举类：0：不是；1：是
 * @author 82320
 *
 */
public enum AddressFirstSelectedEnum {

    NO(0,"不是"),

    YES(1,"是"),

    ;

    Integer code;

    String desc;

    AddressFirstSelectedEnum(Integer code,String desc) {
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
