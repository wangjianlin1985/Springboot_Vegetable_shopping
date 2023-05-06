package com.yjq.programmer.dao.home;

import com.yjq.programmer.pojo.home.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 收藏mapper接口
 */
@Repository
public interface CollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    //通过用户id查找收藏
    List<Collect> findCollectByUserId(Long userId);

    //通过用户id和商品id查找收藏
    Collect findCollectByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}