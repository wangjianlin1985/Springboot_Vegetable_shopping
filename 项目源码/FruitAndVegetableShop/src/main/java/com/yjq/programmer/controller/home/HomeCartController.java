package com.yjq.programmer.controller.home;

import com.yjq.programmer.pojo.home.Cart;
import com.yjq.programmer.service.home.ICartService;
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
 * @create 2020-11-14 22:36
 */

/**
 * 前台地址管理控制器
 */
@RequestMapping("/home/cart")
@Controller
public class HomeCartController {

    @Autowired
    private ICartService cartService;

    /**
     * 购物车首页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        //获取当前用户的购物车
        String id = (String) request.getAttribute("id");
        model.addAttribute("CartVo", cartService.list(Long.valueOf(id)).getData());
        return "home/cart/index";
    }

    /**
     * 添加购物车操作处理
     * @param cart
     * @param request
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> add(Cart cart, HttpServletRequest request){
        //获取当前登录用户的id
        String id = (String) request.getAttribute("id");
        return cartService.add(Long.valueOf(id), cart);
    }

    /**
     * 购物车商品数量变更
     * @param productId
     * @param method
     * @param request
     * @return
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> update(Long productId, String method, HttpServletRequest request){
        //获取当前登录用户的id
        String id = (String) request.getAttribute("id");
        return cartService.update(Long.valueOf(id), productId, method);
    }

    /**
     * 删除购物车商品操作处理
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long productId, HttpServletRequest request){
        //获取当前登录用户的id
        String id = (String) request.getAttribute("id");
        return cartService.delete(Long.valueOf(id), productId);
    }

}
