package com.yjq.programmer.dao.common;

import com.yjq.programmer.pojo.common.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户mapper接口
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据用户名称查找用户
    User findUserByUsername(String username);

    //获取全部用户
    List<User> selectAll();

    //根据搜索内容获取用户
    List<User> selectBySearchContent(String content);
}