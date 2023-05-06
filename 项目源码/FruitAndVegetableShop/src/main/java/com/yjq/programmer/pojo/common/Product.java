package com.yjq.programmer.pojo.common;

import com.yjq.programmer.annotion.ValidateEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 商品Product实体类
 */
public class Product {
    private Long id; //商品id

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=16,minLength=1,errorRequiredMsg="商品名称不能为空！",errorMaxLengthMsg="商品名称长度不能大于16！",errorMinLengthMsg="商品名称长度不能小于1！")
    private String productName; //商品的名称

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=32,minLength=1,errorRequiredMsg="商品详情不能为空！",errorMaxLengthMsg="商品详情长度不能大于32！",errorMinLengthMsg="商品详情长度不能小于1！")
    private String info; //商品的详情

    @ValidateEntity(requiredMaxLength=true,maxLength=256,errorMaxLengthMsg="商品图片路径长度不能大于256！")
    private String productPic; //商品的图片

    @ValidateEntity(required=true,requiredMinValue=true,requiredMaxValue=true,maxValue=100000000.00,minValue=0.00,errorRequiredMsg="商品价格不能为空！",errorMaxValueMsg="商品价格不合理，请调低价格！",errorMinValueMsg="商品价格不能低于0.00元！")
    private BigDecimal price; //商品的价格

    @ValidateEntity(required=true,requiredMinValue=true,requiredMaxValue=true,maxValue=100000000,minValue=0,errorRequiredMsg="商品库存不能为空！",errorMaxValueMsg="商品库存不合理，请调低库存量！",errorMinValueMsg="商品库存不能低于0个！")
    private Integer stock; //商品的库存

    private Integer sellNum; //商品的销售数量

    private Integer commentNum; //商品的评论数量

    @ValidateEntity(required=true,errorRequiredMsg="该商品对应的商品种类不能为空！")
    private Long categoryId; //商品所属的商品种类id

    private ProductCategory productCategory; //商品所属的商品种类

    private Date createTime; //商品创建时间

    private Date updateTime; //商品更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
}