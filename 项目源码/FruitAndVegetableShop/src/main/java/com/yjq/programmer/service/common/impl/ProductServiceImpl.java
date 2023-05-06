package com.yjq.programmer.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.common.ProductMapper;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.service.common.IProductService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-12 13:07
 */

/**
 * 商品service接口实现类
 * @author 82320
 *
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<PageInfo> getProductByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> allProduct = productMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(allProduct);
        pageInfo.setList(allProduct);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> getProductByPageAndContent(Integer pageNum, Integer pageSize, String content) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> selectBySearchContent = productMapper.selectBySearchContent(content);
        PageInfo pageInfo = new PageInfo<>(selectBySearchContent);
        pageInfo.setList(selectBySearchContent);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<Boolean> add(Product product) {
        if(product == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(product);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //把商品信息添加到数据库中
        if(productMapper.insertSelective(product) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_ADD_ERROR);
        }
        return ResponseVo.successByMsg(true, "商品添加成功！");
    }

    @Override
    public ResponseVo<Boolean> edit(Product product) {
        if(product == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //进行统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(product);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //更新数据库中的商品信息
        if(productMapper.updateByPrimaryKeySelective(product) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_EDIT_ERROR);
        }
        return ResponseVo.successByMsg(true, "商品编辑成功！");
    }

    @Override
    public ResponseVo<Boolean> delete(Long id) {
        if(id == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(productMapper.deleteByPrimaryKey(id) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "商品删除成功！");
    }

    @Override
    public ResponseVo<PageInfo> getProductByPageAndCategoryIdAndContent(Integer pageNum, Integer pageSize, Long categoryId, String content) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> allProduct = productMapper.selectByCategoryIdAndSearchContent(categoryId, content);
        PageInfo pageInfo = new PageInfo<>(allProduct);
        pageInfo.setList(allProduct);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectBySellNumber() {
        return productMapper.selectBySellNumber();
    }
}
