package com.yjq.programmer.pojo.common;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 评论Comment实体类
 */
public class Comment {
    private Long id; //评论id

    @ValidateEntity(required=true,errorRequiredMsg="评论对应的商品不能为空！")
    private Long productId; //评论对应的商品id

    private Product product; //评论对应的商品

    @ValidateEntity(required=true,errorRequiredMsg="评论对应的用户不能为空！")
    private Long userId; //评论对应的用户id

    private User user; //评论对应的用户

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=100,minLength=1,errorRequiredMsg="评论内容不能为空！",errorMinLengthMsg="评论内容的长度不能小于1",errorMaxLengthMsg="评论内容的长度不能大于100！")
    private String content; //评论内容

    private Date createTime; //评论创建时间

    private Date updateTime; //评论更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}