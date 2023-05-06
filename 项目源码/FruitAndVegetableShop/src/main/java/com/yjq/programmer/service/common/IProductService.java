package com.yjq.programmer.service.common;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.vo.common.ResponseVo;

import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-12 13:06
 */

/**
 * 商品service接口
 * @author 82320
 *
 */
public interface IProductService {

    //通过分页获取商品列表
    ResponseVo<PageInfo> getProductByPage(Integer pageNum, Integer pageSize);

    //通过分页和搜索内容获取商品列表
    ResponseVo<PageInfo> getProductByPageAndContent(Integer pageNum, Integer pageSize, String content);

    //添加商品操作处理
    ResponseVo<Boolean> add(Product product);

    //编辑商品操作处理
    ResponseVo<Boolean> edit(Product product);

    //删除商品操作处理
    ResponseVo<Boolean> delete(Long id);

    //通过分页和商品种类和搜索内容获取商品列表
    ResponseVo<PageInfo> getProductByPageAndCategoryIdAndContent(Integer pageNum, Integer pageSize, Long categoryId, String content);

    //根据商品id查询商品
    Product selectByPrimaryKey(Long id);

    //获取销量排行前5个商品
    List<Product> selectBySellNumber();
}
