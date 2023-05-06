package com.yjq.programmer.dao.common;

import com.yjq.programmer.pojo.common.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论mapper接口
 */
@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //根据商品id查询评论
    List<Comment> selectByProductId(Long productId);

    //根据搜索内容获取评论
    List<Comment> selectBySearchContent(String content);

    //获取所有评论
    List<Comment> selectAll();

    //根据用户id获取评论
    List<Comment> selectByUserId(Long userId);

}