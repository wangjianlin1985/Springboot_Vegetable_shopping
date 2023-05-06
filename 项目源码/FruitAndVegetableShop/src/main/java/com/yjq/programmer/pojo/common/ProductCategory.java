package com.yjq.programmer.pojo.common;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 商品种类ProductCategory实体类
 */
public class ProductCategory {
    private Long id; //商品种类id

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=8,minLength=1,errorRequiredMsg="商品种类名称不能为空！",errorMaxLengthMsg="商品种类名称长度不能大于8！",errorMinLengthMsg="商品种类名称长度不能小于1！")
    private String categoryName; //商品种类名称

    private Date createTime; //商品种类创建时间

    private Date updateTime; //商品种类更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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