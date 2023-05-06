package com.yjq.programmer.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.common.ProductCategoryMapper;
import com.yjq.programmer.pojo.common.ProductCategory;
import com.yjq.programmer.service.common.IProductCategoryService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-12 9:41
 */

/**
 * 商品种类service接口实现类
 * @author 82320
 *
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ResponseVo<PageInfo> getProductCategoryByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductCategory> allProductCategory = productCategoryMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(allProductCategory);
        pageInfo.setList(allProductCategory);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> getProductCategoryByPageAndContent(Integer pageNum, Integer pageSize, String content) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductCategory> selectBySearchContent = productCategoryMapper.selectBySearchContent(content);
        PageInfo pageInfo = new PageInfo<>(selectBySearchContent);
        pageInfo.setList(selectBySearchContent);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<Boolean> add(ProductCategory productCategory) {
        if(productCategory == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(productCategory);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //向数据库中添加信息
        if(productCategoryMapper.insertSelective(productCategory) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_CATEGORY_ADD_ERROR);
        }
        return ResponseVo.successByMsg(true, "商品种类添加成功！");
    }

    @Override
    public ResponseVo<Boolean> edit(ProductCategory productCategory) {
        if(productCategory == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(productCategory);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //修改数据库中的信息
        if(productCategoryMapper.updateByPrimaryKeySelective(productCategory) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_CATEGORY_EDIT_ERROR);
        }
        return ResponseVo.successByMsg(true, "商品种类修改成功！");
    }

    @Override
    public ResponseVo<Boolean> delete(Long id) {
        if(id == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //删除数据库中对应id的商品种类信息
        if(productCategoryMapper.deleteByPrimaryKey(id) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_CATEGORY_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "商品种类删除成功！");
    }
}
