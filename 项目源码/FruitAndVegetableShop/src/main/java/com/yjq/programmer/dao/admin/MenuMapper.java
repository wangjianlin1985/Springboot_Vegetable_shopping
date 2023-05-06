package com.yjq.programmer.dao.admin;

import com.yjq.programmer.pojo.admin.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * 菜单mapper接口
 * @author 82320
 *
 */
@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);
    
    List<Menu> selectByStateAndPrimaryKeys(@Param("state") Integer state, @Param("menuIdSet") Set<Integer> menuIdSet);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectAll();
    
    //根据菜单状态获取菜单
    List<Menu> selectByState(Integer state);
    
    int getTotal();
}