package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 邮件Mail实体类
 * @author 82320
 *
 */
public class Mail {
    private Integer id; //邮件ID

    @ValidateEntity(required=true,errorRequiredMsg="发件人不能为空！")
    private Integer senderId; //邮件发件人ID

    @ValidateEntity(required=true,errorRequiredMsg="收件人不能为空！")
    private Integer receiverId; //邮件收件人ID

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=64,minLength=1,errorRequiredMsg="主题不能为空！",errorMaxLengthMsg="主题长度不能大于64！",errorMinLengthMsg="主题长度不能小于1！")
    private String title; //邮件主题

    private Integer attachmentOne;  //邮件的附件1

    private Integer attachmentTwo;  //邮件的附件2

    private Integer attachmentThree; //邮件的附件3
    
    private Integer deleteState; //邮件删除状态：1:双方均未删除  2：发送者删除；3：接收者删除  默认为1

    private Date createTime; //邮件创建时间

    private Date updateTime; //邮件更新时间

    private String content;  //邮件正文

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

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getAttachmentOne() {
        return attachmentOne;
    }

    public void setAttachmentOne(Integer attachmentOne) {
        this.attachmentOne = attachmentOne;
    }

    public Integer getAttachmentTwo() {
        return attachmentTwo;
    }

    public void setAttachmentTwo(Integer attachmentTwo) {
        this.attachmentTwo = attachmentTwo;
    }

    public Integer getAttachmentThree() {
        return attachmentThree;
    }

    public void setAttachmentThree(Integer attachmentThree) {
        this.attachmentThree = attachmentThree;
    }

    
    public Integer getDeleteState() {
		return deleteState;
	}

	public void setDeleteState(Integer deleteState) {
		this.deleteState = deleteState;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    public Mail() {
    	
    }
    
    public Mail(Integer id,Integer senderId,Integer receiverId,String title,Integer attachmentOne,Integer attachmentTwo,Integer attachmentThree,Integer deleteState,String content) {
    	this.id = id;
    	this.senderId = senderId;
    	this.receiverId = receiverId;
    	this.title = title;
    	this.attachmentOne = attachmentOne;
    	this.attachmentTwo = attachmentTwo;
    	this.attachmentThree = attachmentThree;
    	this.deleteState = deleteState;
    	this.content = content;
    }
}