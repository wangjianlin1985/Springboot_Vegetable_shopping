package com.yjq.programmer.controller.home;

import com.yjq.programmer.service.common.ICommentService;
import com.yjq.programmer.service.common.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-13 21:19
 */

/**
 * 前台系统商品管理控制器
 */
@RequestMapping("/home/product")
@Controller
public class HomeProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICommentService commentService;

    /**
     * 水果专区商品页面
     * @param model
     * @return
     */
    @RequestMapping(value="/fruit",method= RequestMethod.GET)
    public String fruit(Model model, String content,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize   //每页5个数据
    ){

        model.addAttribute("PageInfo",productService.getProductByPageAndCategoryIdAndContent(pageNum,pageSize,1L,content).getData());
        model.addAttribute("content",content);
        return "home/product/fruit";
    }

    /**
     * 蔬菜专区商品页面
     * @param model
     * @return
     */
    @RequestMapping(value="/vegetable",method= RequestMethod.GET)
    public String vegetable(Model model, String content,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize   //每页5个数据
    ){

        model.addAttribute("PageInfo",productService.getProductByPageAndCategoryIdAndContent(pageNum,pageSize,2L,content).getData());
        model.addAttribute("content",content);
        return "home/product/vegetable";
    }

    /**
     * 商品详情页面
     * @param model
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/detail",method= RequestMethod.GET)
    public String detail(Model model, Long id ,
         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
         @RequestParam(required = false, defaultValue = "5") Integer pageSize //每页5个数据
    ){
        model.addAttribute("TotalComment", commentService.selectByProductId(id).size());
        model.addAttribute("Product", productService.selectByPrimaryKey(id));
        model.addAttribute("SellNumList", productService.selectBySellNumber());
        model.addAttribute("PageInfo", commentService.selectByProductIdAndPage(id, pageNum, pageSize).getData());
        return "home/product/detail";
    }
}
