package com.yjq.programmer.pojo.home;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-15 20:14
 */

import com.yjq.programmer.annotion.ValidateEntity;

/**
 * 购物车Cart实体类
 */
public class Cart {

    @ValidateEntity(required=true,errorRequiredMsg="购买的商品id不能为空！")
    private Long productId;

    @ValidateEntity(required=true,requiredMinValue=true,minValue=1,errorRequiredMsg="购买的商品数量不能为空！",errorMinValueMsg="购买的商品数量不能小于1！")
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart() {
    }

    public Cart(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
