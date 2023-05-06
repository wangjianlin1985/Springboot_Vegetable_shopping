package com.yjq.programmer.pojo.home;

import com.yjq.programmer.annotion.ValidateEntity;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.pojo.common.User;

import java.util.Date;

/**
 * 收藏Collect实体类
 */
public class Collect {
    private Long id; //收藏id

    @ValidateEntity(required=true,errorRequiredMsg="该收藏对应的用户不能为空！")
    private Long userId; //收藏对应的用户id

    private User user; //收藏对应的用户

    @ValidateEntity(required=true,errorRequiredMsg="该收藏对应的商品不能为空！")
    private Long productId; //收藏对应的商品id

    private Product product; //收藏对应的商品

    private Date createTime; //收藏创建时间

    private Date updateTime; //收藏更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collect(Long userId, Long productId){
        this.userId = userId;
        this.productId = productId;
    }

    public Collect(){

    }
}