package com.yjq.programmer.dao.common;

import com.yjq.programmer.pojo.common.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 商品种类mapper接口
 */
@Repository
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    //获取所有商品种类
    List<ProductCategory> selectAll();

    //根据搜索内容获取符合条件的商品种类
    List<ProductCategory> selectBySearchContent(String content);
}