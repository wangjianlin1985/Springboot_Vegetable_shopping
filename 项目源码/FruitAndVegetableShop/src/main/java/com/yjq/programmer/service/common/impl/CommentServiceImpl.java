package com.yjq.programmer.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.common.CommentMapper;
import com.yjq.programmer.dao.common.ProductMapper;
import com.yjq.programmer.pojo.common.Comment;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.service.common.ICommentService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-18 19:16
 */
/**
 * 评论service接口实现类
 * @author 82320
 *
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    @Transactional
    public ResponseVo<Boolean> submitComment(Long uid, Comment comment) {
        comment.setUserId(uid);
        //统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(comment);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //把评论添加到数据库中
        if(commentMapper.insertSelective(comment) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.COMMENT_ADD_ERROR);
        }
        //把商品对应的评论数+1
        Product product = productMapper.selectByPrimaryKey(comment.getProductId());
        product.setCommentNum(product.getCommentNum() + 1);
        if(productMapper.updateByPrimaryKeySelective(product) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.COMMENT_ADD_ERROR);
        }
        return ResponseVo.successByMsg(true, "评论发表成功！");
    }

    @Override
    public ResponseVo<PageInfo> selectByProductIdAndPage(Long productId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.selectByProductId(productId);
        PageInfo pageInfo = new PageInfo<>(commentList);
        pageInfo.setList(commentList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(commentList);
        pageInfo.setList(commentList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> selectByPageAndSearchContent(String content, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.selectBySearchContent(content);
        PageInfo pageInfo = new PageInfo<>(commentList);
        pageInfo.setList(commentList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<Boolean> deleteComment(Long commentId) {
        if(commentId == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if(comment == null){
            return ResponseVo.errorByMsg(CodeMsg.COMMENT_NOT_EXIST);
        }
        if(commentMapper.deleteByPrimaryKey(commentId) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.COMMENT_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "成功删除评论！");
    }

    @Override
    public ResponseVo<PageInfo> selectByPageAndUserId(Integer pageNum, Integer pageSize, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> commentList = commentMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo<>(commentList);
        pageInfo.setList(commentList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public List<Comment> selectByProductId(Long productId){
        return commentMapper.selectByProductId(productId);
    }
}
