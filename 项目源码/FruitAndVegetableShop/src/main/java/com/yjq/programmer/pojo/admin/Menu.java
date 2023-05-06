package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 菜单Menu实体类
 * @author 82320
 *
 */
public class Menu {
    private Integer id; //菜单ID

    @ValidateEntity(required=true,errorRequiredMsg="上级菜单不能为空！")
    private Integer parentId; //上级菜单的ID：默认为0

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,minLength=1,maxLength=32,errorRequiredMsg="菜单名称不能为空！",errorMinLengthMsg="菜单名称长度至少为1!",errorMaxLengthMsg="菜单名称长度不能大于32!")
    private String name; //菜单名称

    @ValidateEntity(requiredMaxLength=true,maxLength=256,errorMaxLengthMsg="菜单路径长度不能大于256！")
    private String url; //菜单路径

    @ValidateEntity(requiredMaxValue=true,requiredMinValue=true,required=true,maxValue=1024,minValue=0,errorMaxValueMsg="菜单排序最大值为1024！",errorMinValueMsg="菜单排序最小值为0！",errorRequiredMsg="菜单排序不能为空！")
    private Integer sort; //菜单排序：默认为0，值越大则在同级别越优先显示

    @ValidateEntity(required=true,requiredMaxLength=true,maxLength=64,errorRequiredMsg="菜单图标不能为空！",errorMaxLengthMsg="菜单图标长度不能大于64！")
    private String icon; //菜单图标

    @ValidateEntity(required=true,errorRequiredMsg="菜单状态不能为空！")
    private Integer state; //菜单状态：1:开启；2：停用

    private Date createTime; //菜单创建时间

    private Date updateTime; //菜单更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    
    public Menu() {
    	
    }
  
    public Menu(Integer id,Integer parentId,String name,String url,Integer sort,String icon,Integer state) {
    	this.id = id;
    	this.parentId = parentId;
    	this.name = name;
    	this.url = url;
    	this.sort = sort;
    	this.icon = icon;
    	this.state = state;
    }
    
}