package com.yjq.programmer.controller.home;

import com.yjq.programmer.pojo.common.Comment;
import com.yjq.programmer.service.common.ICommentService;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-18 19:06
 */

/**
 * 前台评论管理控制器
 */
@RequestMapping("/home/comment")
@Controller
public class HomeCommentController {

    @Autowired
    private ICommentService commentService;

    /**
     * 发表评论操作处理
     * @param comment
     * @param request
     * @return
     */
    @RequestMapping(value="/submit_comment",method= RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> submitComment(Comment comment, HttpServletRequest request){
        //获取当前登录用户的id
        String uid = (String) request.getAttribute("id");
        return commentService.submitComment(Long.valueOf(uid), comment);
    }
}
