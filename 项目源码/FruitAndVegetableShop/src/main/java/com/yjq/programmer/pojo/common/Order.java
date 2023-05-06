package com.yjq.programmer.pojo.common;

import com.yjq.programmer.pojo.home.Address;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单Order实体类
 */
public class Order {
    private Long id;  //订单id

    private Long orderNo; //订单流水号

    private Long userId; //订单所属用户id

    private User user; //订单所属用户

    private Integer state; //订单状态  0：未支付；1：已支付，待发货；2：已取消；3：已送达，待签收；4：已签收；5：已发货

    private BigDecimal totalPrice; //订单总价

    private Long addressId; //订单对应的配送地址id

    private Address address; //订单对应的配送地址

    private String remark; //订单留言

    private Integer isDeleted; //用户是否删除订单 0：未删除；1：已删除

    private List<OrderItem> orderItemList; //订单对应的订单详情

    private Date createTime; //订单创建时间

    private Date updateTime; //订单更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order(){

    }

    public Order(Long orderNo, Long userId, Integer state, BigDecimal totalPrice){
        this.orderNo = orderNo;
        this.userId = userId;
        this.state = state;
        this.totalPrice = totalPrice;
    }
}