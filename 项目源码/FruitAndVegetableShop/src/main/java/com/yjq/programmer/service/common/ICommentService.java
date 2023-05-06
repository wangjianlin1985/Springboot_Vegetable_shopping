package com.yjq.programmer.service.common;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.common.Comment;
import com.yjq.programmer.vo.common.ResponseVo;

import java.util.List;
/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-18 19:16
 */

/**
 * 评论service接口
 * @author 82320
 *
 */
public interface ICommentService {

    //发表评论操作处理
    ResponseVo<Boolean> submitComment(Long uid, Comment comment);

    //根据商品id和分页查询评论
    ResponseVo<PageInfo> selectByProductIdAndPage(Long productId, Integer pageNum, Integer pageSize);

    //根据分页查询评论
    ResponseVo<PageInfo> selectByPage(Integer pageNum, Integer pageSize);

    //根据分页和搜索内容获取评论
    ResponseVo<PageInfo> selectByPageAndSearchContent(String content, Integer pageNum, Integer pageSize);

    //删除评论操作处理
    ResponseVo<Boolean> deleteComment(Long commentId);

    //根据分页和用户id获取评论
    ResponseVo<PageInfo> selectByPageAndUserId(Integer pageNum, Integer pageSize, Long userId);

    //根据商品id获取评论
    List<Comment> selectByProductId(Long productId);
}
