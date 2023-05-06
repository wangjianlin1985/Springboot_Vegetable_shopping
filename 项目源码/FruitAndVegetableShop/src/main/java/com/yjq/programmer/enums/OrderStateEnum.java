package com.yjq.programmer.enums;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-16 16:43
 */
/**
 * 订单状态枚举类：0：未支付；1：已支付，待发货；2：已取消；3：已送达，待签收；4：已签收；5：已发货
 * @author 82320
 *
 */
public enum OrderStateEnum {

    NO_PAY(0,"未支付"),

    PAYED(1,"已支付，代发货"),

    CANCELED(2,"已取消"),

    ARRIVED(3,"已送达，待签收"),

    SIGN(4, "已签收"),

    SEND(5, "已发货"),

            ;

    Integer code;

    String desc;

    OrderStateEnum(Integer code,String desc) {
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
