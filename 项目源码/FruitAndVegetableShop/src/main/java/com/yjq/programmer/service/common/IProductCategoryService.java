package com.yjq.programmer.service.common;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.common.ProductCategory;
import com.yjq.programmer.vo.common.ResponseVo;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-12 9:40
 */

/**
 * 商品种类service接口
 * @author 82320
 *
 */
public interface IProductCategoryService {

    //通过分页获取商品种类列表
    ResponseVo<PageInfo> getProductCategoryByPage(Integer pageNum, Integer pageSize);

    //通过分页和搜索内容获取商品种类列表
    ResponseVo<PageInfo> getProductCategoryByPageAndContent(Integer pageNum, Integer pageSize, String content);

    //添加商品种类操作处理
    ResponseVo<Boolean> add(ProductCategory productCategory);

    //修改商品种类操作处理
    ResponseVo<Boolean> edit(ProductCategory productCategory);

    //删除商品种类操作处理
    ResponseVo<Boolean> delete(Long id);
}
