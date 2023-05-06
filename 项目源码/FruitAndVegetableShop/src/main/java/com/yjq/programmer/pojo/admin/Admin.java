package com.yjq.programmer.pojo.admin;

import com.yjq.programmer.annotion.ValidateEntity;

import java.util.Date;

/**
 * 管理员Admin实体类
 * @author 82320
 *
 */
public class Admin {
	
    private Integer id; //管理员ID
    
    @ValidateEntity(required=true,errorRequiredMsg="管理员对应角色不能为空！")
    private Integer roleId; //管理员对应角色ID

    @ValidateEntity(requiredMaxLength=true,maxLength=256,errorMaxLengthMsg="管理员头像长度不能大于256！")
    private String headPic; //管理员头像

    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=16,minLength=1,errorRequiredMsg="管理员名称不能为空！",errorMaxLengthMsg="管理员名称长度不能大于16！",errorMinLengthMsg="管理员名称长度不能小于1！")
    private String name; //管理员姓名
    
    @ValidateEntity(required=true,requiredMaxLength=true,requiredMinLength=true,maxLength=16,minLength=5,errorRequiredMsg="管理员密码不能为空！",errorMaxLengthMsg="管理员密码长度不能大于16！",errorMinLengthMsg="管理员密码长度不能小于5！")
    private String password; //管理员密码   默认为：123456

    private Integer sex; //管理员性别：1：男；2：女；3：未知    默认为3：未知

    @ValidateEntity(requiredMaxLength=true,maxLength=128,errorMaxLengthMsg="管理员地址长度不能大于128！")
    private String address; //管理员地址
    
    private Long mobile; //管理员电话

    @ValidateEntity(required=true,errorRequiredMsg="管理员状态不能为空！")
    private Integer state; //管理员状态：1：启用；2：冻结  

    private Date createTime; //管理员创建时间

    private Date updateTime; //管理员更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
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
    
    public Admin() {
    	
    }
    
  
    public Admin(Integer id,Integer roleId,String headPic,String name,String password,Integer sex,String address,Long mobile,Integer state) {
    	this.id = id;
    	this.roleId = roleId;
    	this.headPic = headPic;
    	this.name = name;
    	this.password = password;
    	this.sex = sex;
    	this.address = address;
    	this.mobile = mobile;
    	this.state = state;
    }
    
}