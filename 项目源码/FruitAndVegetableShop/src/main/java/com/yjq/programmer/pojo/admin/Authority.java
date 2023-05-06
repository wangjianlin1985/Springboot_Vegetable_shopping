package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 权限Authority实体类
 * @author 82320
 *
 */
public class Authority {
    private Integer id; //权限ID

    @ValidateEntity(required=true,errorRequiredMsg="权限对应菜单ID不能为空！")
    private Integer menuId; //权限对应的菜单ID

    @ValidateEntity(required=true,errorRequiredMsg="权限对应角色ID不能为空！")
    private Integer roleId; //权限对应的角色ID

    private Date createTime; //权限创建时间

    private Date updateTime; //权限更改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
    
    public Authority() {
    	
    }
    
    public Authority(Integer id,Integer menuId,Integer roleId) {
    	this.id = id;
    	this.menuId = menuId;
    	this.roleId = roleId;
    }
}