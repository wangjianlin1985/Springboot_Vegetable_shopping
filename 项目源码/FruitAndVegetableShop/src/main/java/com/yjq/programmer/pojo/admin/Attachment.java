package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 附件Attachment实体类
 * @author 82320
 *
 */
public class Attachment {
    private Integer id; //附件ID
    
    @ValidateEntity(required=true,errorRequiredMsg="发件人不能为空！")
    private Integer senderId;  //发件人ID

    @ValidateEntity(required=true,requiredMinLength=true,requiredMaxLength=true,maxLength=256,minLength=1,errorMaxLengthMsg="附件保存路径长度不能大于256！",errorMinLengthMsg="附件保存路径长度不能小于1！",errorRequiredMsg="附件保存路径不能为空！")
    private String url; //附件保存路径

    @ValidateEntity(required=true,requiredMinLength=true,requiredMaxLength=true,maxLength=256,minLength=1,errorMaxLengthMsg="附件名称长度不能大于256！",errorMinLengthMsg="附件名称长度不能小于1！",errorRequiredMsg="附件名称不能为空！")
    private String name; //附件名称

    @ValidateEntity(required=true,errorRequiredMsg="附件大小不能为空！")
    private BigDecimal size; //附件大小：单位为KB；默认为0.00

    private Date createTime;  //附件创建时间

    private Date updateTime;  //附件更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
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
    
    public Attachment() {
    	
    }
    
    public Attachment(Integer id,Integer senderId,String url,String name,BigDecimal size) {
    	this.id = id;
    	this.senderId = senderId;
    	this.url = url;
    	this.name = name;
    	this.size = size;
    }
}