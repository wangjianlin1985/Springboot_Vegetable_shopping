package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 公告Announcement实体类
 * @author 82320
 *
 */
public class Announcement {
    private Integer id; //公告ID

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=256,minLength=1,errorRequiredMsg="公告内容不能为空！",errorMaxLengthMsg="公告内容长度不能大于256！",errorMinLengthMsg="公告内容长度不能小于1！")
    private String content;  //公告内容

    @ValidateEntity(required=true,errorRequiredMsg="公告发布对应管理员角色不能为空！")
    private Integer adminId; //公告发布所属管理员

    private Date createTime; //公告创建时间

    private Date updateTime; //公告更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
    
    public Announcement() {
    	
    }
    
    public Announcement (Integer id,String content,Integer adminId) {
    	this.id = id;
    	this.content = content;
    	this.adminId = adminId;
    }
}