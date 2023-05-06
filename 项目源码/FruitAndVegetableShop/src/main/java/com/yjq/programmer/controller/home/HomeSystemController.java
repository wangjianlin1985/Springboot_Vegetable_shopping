package com.yjq.programmer.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-07 9:40
 */

/**
 * 前台系统管理控制器
 */
@RequestMapping("/home/system")
@Controller
public class HomeSystemController {

    /**
     * 系统首页页面
     * @param model
     * @return
     */
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model) {
        return "home/system/index";
    }
}
