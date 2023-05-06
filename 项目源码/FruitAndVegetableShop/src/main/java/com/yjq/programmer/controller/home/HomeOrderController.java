package com.yjq.programmer.controller.home;

import com.yjq.programmer.enums.AddressFirstSelectedEnum;
import com.yjq.programmer.pojo.common.Order;
import com.yjq.programmer.service.common.IOrderService;
import com.yjq.programmer.service.home.IAddressService;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-16 19:11
 */

/**
 * 前台订单管理控制器
 */
@RequestMapping("/home/order")
@Controller
public class HomeOrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IAddressService addressService;

    /**
     * 订单结算页面
     * @param model
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model, Long orderId, HttpServletRequest request){
        //获取当前登录用户的id
        String uid = (String) request.getAttribute("id");
        Order order = orderService.selectByOrderIdAndUserId(Long.valueOf(uid), orderId);
        if(order == null){
            return "error/404";
        }
        model.addAttribute("Order", order);
        model.addAttribute("Address", addressService.selectByUserIdAndFirstSelected(Long.valueOf(uid), AddressFirstSelectedEnum.YES.getCode()));
        return "home/order/index";
    }


    /**
     * 订单生成操作处理
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value="/generate",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Long> generate(String ids, HttpServletRequest request){
        return orderService.generate(ids, request);
    }


    /**
     * 订单提交操作处理
     * @param remark
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping(value="/submit",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> submit(String remark, Long orderId, HttpServletRequest request){
        //获取当前登录用户的id和邮箱
        String uid = (String) request.getAttribute("id");
        String email = (String) request.getAttribute("email");
        return orderService.submit(remark, orderId, Long.valueOf(uid), email);
    }

    /**
     * 修改订单状态
     * @param orderId
     * @param state
     * @return
     */
    @RequestMapping(value="/update_order_state",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> updateOrderState(Long orderId, Integer state){
        return orderService.updateOrderState(orderId, state);
    }

    /**
     * 用户删除订单操作处理
     * @param orderId
     * @param isDeleted
     * @return
     */
    @RequestMapping(value="/user_delete",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> userDelete(Long orderId, Integer isDeleted){
        return orderService.userDelete(orderId, isDeleted);
    }

}
