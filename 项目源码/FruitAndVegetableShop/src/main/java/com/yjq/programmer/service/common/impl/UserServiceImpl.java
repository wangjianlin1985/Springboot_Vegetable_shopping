package com.yjq.programmer.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.common.UserMapper;
import com.yjq.programmer.enums.MailTypeEnum;
import com.yjq.programmer.pojo.common.User;
import com.yjq.programmer.service.common.IUserService;
import com.yjq.programmer.util.JWTUtil;
import com.yjq.programmer.util.MailUtil;
import com.yjq.programmer.util.StringUtil;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-07 10:56
 */

/**
 * 用户service接口实现类
 * @author 82320
 *
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseVo<Boolean> isUsernameExist(User user, Long id) {
        User findByUsername = userMapper.findUserByUsername(user.getUsername());
        if(findByUsername != null) {
            if(!findByUsername.getId().equals(id)) {
                return ResponseVo.success(true); //出现重名
            }
        }
        return ResponseVo.success(false);//没有重名
    }

    @Override
    public ResponseVo<Boolean> login(String username, String password) {
        //判断用户输入的用户名称是否为空
        if(StringUtil.isEmpty(username)){
            return ResponseVo.errorByMsg(CodeMsg.USER_USERNAME_EMPTY);
        }
        //判断用户输入的密码是否为空
        if(StringUtil.isEmpty(password)){
            return ResponseVo.errorByMsg(CodeMsg.USER_PASSWORD_EMPTY);
        }
        //判断用户是否存在
        User findUser = userMapper.findUserByUsername(username);
        if(findUser == null){
            return ResponseVo.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        //判断用户输入的信息是否正确
        if(!password.equals(findUser.getPassword())){
            return ResponseVo.errorByMsg(CodeMsg.USERNAME_OR_PASSWORD_ERROR);
        }
        //到这一步，说明用户身份验证已经通过，准备使用JWT获取token
        Map<String,String> map = new HashMap<>();
        map.put("id",findUser.getId().toString());
        map.put("username",findUser.getUsername());
        map.put("email",findUser.getEmail());
        map.put("phone",findUser.getPhone());
        map.put("headPic",findUser.getHeadPic());
        String token = JWTUtil.getToken(map);

        return ResponseVo.successByMsg(true, token);
    }

    @Override
    public ResponseVo<Boolean> register(User user, String repassword, String cpacha, HttpServletRequest request) {
        if(user == null) {
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //验证确认密码和验证码是否为空
        if(StringUtil.isEmpty(repassword)){
            return ResponseVo.errorByMsg(CodeMsg.USER_REPASSWORD_EMPTY);
        }
        if(StringUtil.isEmpty(cpacha)){
            return ResponseVo.errorByMsg(CodeMsg.CPACHA_EMPTY);
        }
        //验证两次密码输入是否一致
        if(!user.getPassword().equals(repassword)){
            return ResponseVo.errorByMsg(CodeMsg.USER_REPASSWORD_ERROR);
        }
        //判断验证码输入是否正确
        String collectCpacha = (String) request.getSession().getAttribute("user_register");
        if(StringUtil.isEmpty(collectCpacha)){
            return ResponseVo.errorByMsg(CodeMsg.CPACHA_EXPIRE);
        }
        if(!cpacha.toLowerCase().equals(collectCpacha.toLowerCase())) {
            return ResponseVo.errorByMsg(CodeMsg.CPACHA_ERROR);
        }
        //判断用户名称是否已经存在
        if(isUsernameExist(user, 0L).getData()){
            return ResponseVo.errorByMsg(CodeMsg.USER_USERNAME_ALREADY_EXIST);
        }
        //把用户信息添加到数据库
        if(userMapper.insertSelective(user) <= 0) {
            return ResponseVo.errorByMsg(CodeMsg.USER_ADD_ERROR);
        }
        //发送邮件提醒用户成功注册
        MailUtil.sendMail(MailTypeEnum.USER_REGISTER.getCode(), user.getEmail(), "");
        return ResponseVo.successByMsg(true,"注册成功！快去登录体验吧！");
    }

    @Override
    public ResponseVo<String> updateInfo(User user) {
        if(user == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //获取用户密码以便通过下面统一表单验证
        User findUserById = userMapper.selectByPrimaryKey(user.getId());
        user.setPassword(findUserById.getPassword());
        //统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //修改数据库中用户信息
        if(userMapper.updateByPrimaryKeySelective(user) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.USER_INFO_EDIT_ERROR);
        }
        Map<String,String> map = new HashMap<>();
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("email",user.getEmail());
        map.put("phone",user.getPhone());
        map.put("headPic",user.getHeadPic());
        String token = JWTUtil.getToken(map);

        return ResponseVo.successByMsg(token,"修改个人信息成功！");
    }

    @Override
    public ResponseVo<Boolean> updatePasswd(String prePassword, String newPassword, String reNewPassword, HttpServletRequest request) {
        //对用户输入的数据进行非空验证
        if(StringUtil.isEmpty(prePassword)){
            return ResponseVo.errorByMsg(CodeMsg.USER_PREPASSWORD_EMPTY);
        }
        if(StringUtil.isEmpty(newPassword)){
            return ResponseVo.errorByMsg(CodeMsg.USER_NEWPASSWORD_EMPTY);
        }
        if(StringUtil.isEmpty(reNewPassword)){
            return ResponseVo.errorByMsg(CodeMsg.USER_RENEWPASSWORD_EMPTY);
        }
        //获取当前登录用户的id
        String id = (String) request.getAttribute("id");
        //判断用户输入的旧密码是否正确
        User user = userMapper.selectByPrimaryKey(Long.valueOf(id));
        if(!prePassword.equals(user.getPassword())){
            return ResponseVo.errorByMsg(CodeMsg.USER_PREPASSWORD_ERROR);
        }
        //判断用户输入的新密码是否符合规范
        user.setPassword(newPassword);
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //判断用户输入的新密码和确认新密码是否一致
        if(!newPassword.equals(reNewPassword)){
            return ResponseVo.errorByMsg(CodeMsg.USER_RENEWPASSWORD_ERROR);
        }
        //修改数据库中的用户密码信息
        if(userMapper.updateByPrimaryKeySelective(user) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.USER_PASSWORD_EDIT_ERROR);
        }
        return ResponseVo.successByMsg(true, "修改密码成功！");
    }

    @Override
    public ResponseVo<PageInfo> getUserByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(userList);
        pageInfo.setList(userList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> getUserByPageAndContent(Integer pageNum, Integer pageSize, String content) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectBySearchContent(content);
        PageInfo pageInfo = new PageInfo<>(userList);
        pageInfo.setList(userList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResponseVo<Boolean> updateUserPasswd(String passwd, Long userId) {
        if(StringUtil.isEmpty(passwd) || userId == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(passwd);
        //统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //修改数据库中的用户信息
        if(userMapper.updateByPrimaryKeySelective(user) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.USER_PASSWORD_EDIT_ERROR);
        }
        return ResponseVo.successByMsg(true, "修改用户密码成功！");
    }

    @Override
    public ResponseVo<Boolean> deleteUser(Long userId) {
        if(userId == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ResponseVo.errorByMsg(CodeMsg.USER_NOT_EXIST);
        }
        if(userMapper.deleteByPrimaryKey(userId) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.USER_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "删除用户成功！");
    }

}
