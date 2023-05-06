package com.yjq.programmer.pojo.common;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 用户User实体类
 */
public class User {
    private Long id; //用户id

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=8,minLength=1,errorRequiredMsg="用户名称不能为空！",errorMaxLengthMsg="用户名称长度不能大于8！",errorMinLengthMsg="用户名称长度不能小于1！")
    private String username; //用户名称

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=16,minLength=6,errorRequiredMsg="密码不能为空！",errorMaxLengthMsg="密码长度不能大于16！",errorMinLengthMsg="密码长度不能小于6！")
    private String password; //用户密码

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=64,minLength=1,errorRequiredMsg="电子邮箱不能为空！",errorMaxLengthMsg="电子邮箱长度不能大于64！",errorMinLengthMsg="电子邮箱长度不能小于1！")
    private String email; //用户电子邮箱

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=11,minLength=11,errorRequiredMsg="手机号码不能为空！",errorMaxLengthMsg="请输入11位手机号码！",errorMinLengthMsg="请输入11位手机号码！")
    private String phone; //用户手机号码

    @ValidateEntity(requiredMaxLength=true,maxLength=256,errorMaxLengthMsg="头像路径长度不能大于256！")
    private String headPic; //用户头像

    private Date createTime; //用户信息创建时间

    private Date updateTime; //用户信息更改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
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