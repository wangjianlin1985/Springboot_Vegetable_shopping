package com.yjq.programmer.service.common;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.common.User;
import com.yjq.programmer.vo.common.ResponseVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-07 10:55
 */

/**
 * 用户service接口
 * @author 82320
 *
 */
public interface IUserService {

    //判断用户名称是否有重名
    ResponseVo<Boolean> isUsernameExist(User user, Long id);

    //用户登录操作处理
    ResponseVo<Boolean> login(String username, String password);

    //用户注册操作处理
    ResponseVo<Boolean> register(User user, String repassword, String cpacha, HttpServletRequest request);

    //用户个人信息修改操作处理
    ResponseVo<String> updateInfo(User user);

    //用户修改密码操作处理
    ResponseVo<Boolean> updatePasswd(String prePassword, String newPassword, String reNewPassword, HttpServletRequest request);

    //根据分页获取用户列表
    ResponseVo<PageInfo> getUserByPage(Integer pageNum, Integer pageSize);

    //根据分页和搜索内容获取用户列表
    ResponseVo<PageInfo> getUserByPageAndContent(Integer pageNum, Integer pageSize, String content);

    //根据用户id查询用户
    User selectByPrimaryKey(Long id);

    //修改用户密码操作处理
    ResponseVo<Boolean> updateUserPasswd(String passwd, Long userId);

    //删除用户操作处理
    ResponseVo<Boolean> deleteUser(Long userId);
}
