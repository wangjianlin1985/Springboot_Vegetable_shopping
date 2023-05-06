package com.yjq.programmer.pojo.home;

import com.yjq.programmer.annotion.ValidateEntity;
import com.yjq.programmer.enums.AddressFirstSelectedEnum;

import java.util.Date;

/**
 * 地址Address实体类
 */
public class Address {
    private Long id; //地址id

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=8,minLength=1,errorRequiredMsg="收货人姓名不能为空！",errorMaxLengthMsg="收货人姓名长度不能大于8！",errorMinLengthMsg="收货人姓名长度不能小于1！")
    private String receiverName; //收货人姓名

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=64,minLength=1,errorRequiredMsg="收货人地址不能为空！",errorMaxLengthMsg="收货人地址长度不能大于64！",errorMinLengthMsg="收货人地址长度不能小于1！")
    private String receiverAddress; //收货人地址

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=11,minLength=11,errorRequiredMsg="收货人手机号码不能为空！",errorMaxLengthMsg="请输入11位收货人手机号码！",errorMinLengthMsg="请输入11位收货人手机号码！")
    private String receiverPhone; //收获人手机号码

    @ValidateEntity(required=true,errorRequiredMsg="该地址对应的用户不能为空！")
    private Long userId; //地址对应的用户id

    private Integer firstSelected = AddressFirstSelectedEnum.NO.getCode(); //该地址是否为首选地址 0：不是 1：是  默认：0(不是)

    private Date createTime; //地址创建时间

    private Date updateTime; //地址更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getFirstSelected() {
        return firstSelected;
    }

    public void setFirstSelected(Integer firstSelected) {
        this.firstSelected = firstSelected;
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