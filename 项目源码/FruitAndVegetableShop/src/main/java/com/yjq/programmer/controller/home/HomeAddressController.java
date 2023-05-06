package com.yjq.programmer.controller.home;

import com.yjq.programmer.pojo.home.Address;
import com.yjq.programmer.service.home.IAddressService;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-11 15:27
 */

/**
 * 前台地址管理控制器
 */
@RequestMapping("/home/address")
@Controller
public class HomeAddressController {

    @Autowired
    private IAddressService addressService;

    /**
     * 地址管理页面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        //获取当前用户的所有地址
        String id = (String) request.getAttribute("id");
        List<Address> addressByUserId = addressService.findAddressByUserId(Long.valueOf(id));
        model.addAttribute("addressList", addressByUserId);
        return "home/address/index";
    }

    /**
     * 添加地址操作处理
     * @param address
     * @param request
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> add(Address address, HttpServletRequest request){
        return addressService.add(address, request);
    }

    /**
     * 设置订单首选地址操作处理
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value="/set_first_selected",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> setFirstSelected(Long id, HttpServletRequest request){
        return addressService.setFirstSelected(id, request);
    }

    /**
     * 删除地址操作处理
     * @param id
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long id){
        return addressService.delete(id);
    }
}
