package com.yjq.programmer.dao.admin;

import com.yjq.programmer.pojo.admin.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色mapper接口
 * @author 82320
 *
 */
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);
    
    Role selectByName(String name);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> selectAll();
    
    List<Role> selectBySearchName(String name);
    
    int getTotal();
}