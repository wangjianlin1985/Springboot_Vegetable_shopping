package com.yjq.programmer.vo.home;

import java.math.BigDecimal;
/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-15 22:01
 */

/**
 * 购物车商品返回类
 */
public class CartProductVo {
    private Long productId; //商品id

    private String productName; //商品名称

    private String productPic; //商品图片

    private BigDecimal price; //商品单价

    private BigDecimal productTotalPrice; //商品小计

    private Integer quantity; //商品购买数量

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartProductVo(){

    }

    public CartProductVo(Long productId, Integer quantity){
        this.productId = productId;
        this.quantity = quantity;
    }
}
