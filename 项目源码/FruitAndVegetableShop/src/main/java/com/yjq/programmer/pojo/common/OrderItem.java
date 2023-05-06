package com.yjq.programmer.pojo.common;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情OrderItem实体类
 */
public class OrderItem {
    private Long id; //订单详情id

    private Long orderId; //订单详情对应的订单id

    private Long productId; //订单详情对应的商品id

    private String productName; //商品名称

    private String productPic; //商品图片

    private BigDecimal productPrice; //商品单价

    private Integer quantity; //商品购买数量

    private BigDecimal totalPrice; //商品小计

    private Date createTime; //订单详情创建时间

    private Date updateTime; //订单详情更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic == null ? null : productPic.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public OrderItem(){

    }

    public OrderItem(Long productId, Integer quantity,String productName, String productPic, BigDecimal productPrice, BigDecimal totalPrice){
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productPic = productPic;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
    }
}