package com.yjq.programmer.controller.home;

import com.yjq.programmer.enums.OrderDeleteEnum;
import com.yjq.programmer.enums.OrderStateEnum;
import com.yjq.programmer.pojo.common.User;
import com.yjq.programmer.service.common.ICommentService;
import com.yjq.programmer.service.common.IOrderService;
import com.yjq.programmer.service.common.IUserService;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-07 10:22
 */

/**
 * 前台用户管理控制器
 */
@RequestMapping("/home/user")
@Controller
public class HomeUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ICommentService commentService;

    /**
     * 注册页面
     * @param model
     * @return
     */
    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String register(Model model) {
        return "home/user/register";
    }

    /**
     * 登录页面
     * @param model
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(Model model) {
        return "home/user/login";
    }

    /**
     * 个人信息页面
     * @param model
     * @return
     */
    @RequestMapping(value="/info",method=RequestMethod.GET)
    public String info(Model model) {
        return "home/user/info";
    }

    /**
     * 修改密码页面
     * @param model
     * @return
     */
    @RequestMapping(value="/update_passwd",method=RequestMethod.GET)
    public String updatePasswd(Model model) {
        return "home/user/update_passwd";
    }

    /**
     * 我的评论页面
     * @param model
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/comment",method=RequestMethod.GET)
    public String comment(Model model,HttpServletRequest request,
                          @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
    ){
        String uid = (String) request.getAttribute("id");
        model.addAttribute("PageInfo", commentService.selectByPageAndUserId(pageNum, pageSize, Long.valueOf(uid)).getData());
        return "home/user/comment";
    }

    /**
     * 我的订单页面
     * @param model
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/order",method=RequestMethod.GET)
    public String order(Model model, HttpServletRequest request,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "1") Integer pageSize //每页1个数据
    ){
        //获取当前登录用户的id
        String uid = (String) request.getAttribute("id");
        model.addAttribute("PageInfo", orderService.selectByPageAndUserIdAndIsDeleted(Long.valueOf(uid), OrderDeleteEnum.NO.getCode(), pageNum, pageSize).getData());
        //model.addAttribute("OrderList", orderMapper.selectByUserIdAndIsDeleted(Long.valueOf(uid), 0));
        model.addAttribute("Send", orderService.selectByOrderStateAndUserIdAndIsDeleted(OrderStateEnum.SEND.getCode(), Long.valueOf(uid), OrderDeleteEnum.NO.getCode()).size());
        model.addAttribute("Sign", orderService.selectByOrderStateAndUserIdAndIsDeleted(OrderStateEnum.SIGN.getCode(), Long.valueOf(uid), OrderDeleteEnum.NO.getCode()).size());
        return "/home/user/order";
    }


    /**
     * 用户登录操作处理
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> login(String username, String password){
        return userService.login(username, password);
    }

    /**
     * 用户注册操作处理
     * @param user
     * @param repassword
     * @param cpacha
     * @param request
     * @return
     */
    @RequestMapping(value="/register",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> register(User user, String repassword, String cpacha, HttpServletRequest request) {
        return userService.register(user, repassword, cpacha, request);
    }

    /**
     * 用户个人信息修改操作处理
     * @param user
     * @return
     */
    @RequestMapping(value="/update_info",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<String> updateInfo(User user){
        return userService.updateInfo(user);
    }

    /**
     * 用户修改密码操作处理
     * @param prePassword
     * @param newPassword
     * @param reNewPassword
     * @param request
     * @return
     */
    @RequestMapping(value="/update_passwd",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> updatePasswd(String prePassword, String newPassword, String reNewPassword, HttpServletRequest request){
        return userService.updatePasswd(prePassword, newPassword, reNewPassword, request);
    }


}
