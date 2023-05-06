package com.yjq.programmer.vo.home;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-15 22:12
 */

/**
 * 购物车返回类
 */
public class CartVo {

    private List<CartProductVo> cartProductVoList; //购物车中商品列表

    private BigDecimal cartTotalPrice; //购物车总价

    public List<CartProductVo> getCartProductVoList() {
        return cartProductVoList;
    }

    public void setCartProductVoList(List<CartProductVo> cartProductVoList) {
        this.cartProductVoList = cartProductVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }
}
