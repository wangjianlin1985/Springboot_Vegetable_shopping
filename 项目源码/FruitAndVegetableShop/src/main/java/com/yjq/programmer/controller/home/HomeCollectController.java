package com.yjq.programmer.controller.home;

import com.yjq.programmer.service.home.ICollectService;
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
 * @create 2020-11-14 17:04
 */

/**
 * 前台收藏管理控制器
 */
@RequestMapping("/home/collect")
@Controller
public class HomeCollectController {

    @Autowired
    private ICollectService collectService;

    /**
     * 收藏管理页面
     * @param model
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "4") Integer pageSize //每页4个数据
    ) {
        //获取当前用户的所有收藏
        String id = (String) request.getAttribute("id");
        model.addAttribute("PageInfo", collectService.getCollectByPageAndUserId(pageNum,pageSize,Long.valueOf(id)).getData());
        return "home/collect/index";
    }

    /**
     * 添加收藏操作处理
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> add(Long id, HttpServletRequest request){
        return collectService.add(id, request);
    }


    /**
     * 删除收藏操作处理
     * @param id
     * @return
     */
    @RequestMapping(value="/delete",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> delete(Long id){
        return collectService.delete(id);
    }


}
