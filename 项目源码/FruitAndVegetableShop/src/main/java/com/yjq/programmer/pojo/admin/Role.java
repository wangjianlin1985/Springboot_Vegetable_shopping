package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 角色Role实体类
 * @author 82320
 *
 */
public class Role {
    private Integer id; //角色ID

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=32,minLength=1,errorRequiredMsg="角色名称不能为空！",errorMaxLengthMsg="角色名称长度不能大于32！",errorMinLengthMsg="角色名称长度不能小于1!")
    private String name; //角色名称

    @ValidateEntity(requiredMaxLength=true,maxLength=128,errorMaxLengthMsg="角色描述长度不能大于128！")
    private String description; //角色描述

    private Date createTime; //角色创建时间

    private Date updateTime;  //角色更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
    
    public Role() {
    	
    }
    
    public Role(Integer id,String name,String description) {
    	this.id = id;
    	this.name = name;
    	this.description = description;
    }
}