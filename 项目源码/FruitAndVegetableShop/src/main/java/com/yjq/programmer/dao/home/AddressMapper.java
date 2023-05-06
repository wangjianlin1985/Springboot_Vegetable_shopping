package com.yjq.programmer.dao.home;

import com.yjq.programmer.pojo.home.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 地址mapper接口
 */
@Repository
public interface AddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    //根据用户id查找地址
    List<Address> findAddressByUserId(Long userId);

    //根据用户id和地址是否首选来获取地址
    Address selectByUserIdAndFirstSelected(@Param("userId") Long userId, @Param("firstSelected") Integer firstSelected);

    //根据用户id设置地址是否首选
    int updateFirstSelectedByUserId(@Param("userId") Long userId, @Param("firstSelected") Integer firstSelected);

}