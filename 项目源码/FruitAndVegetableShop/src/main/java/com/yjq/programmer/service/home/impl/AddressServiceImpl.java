package com.yjq.programmer.service.home.impl;

import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.home.AddressMapper;
import com.yjq.programmer.enums.AddressFirstSelectedEnum;
import com.yjq.programmer.pojo.home.Address;
import com.yjq.programmer.service.home.IAddressService;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-11 12:55
 */

/**
 * 地址service接口实现类
 * @author 82320
 *
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ResponseVo<Boolean> add(Address address, HttpServletRequest request) {
        if(address == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //获取当前登录用户的id
        String id = (String) request.getAttribute("id");
        address.setUserId(Long.valueOf(id));
        //统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(address);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        //判断用户是否已存三个地址，若是，则不让添加
        List<Address> addressByUserId = addressMapper.findAddressByUserId(Long.valueOf(id));
        if(addressByUserId.size() >= 3){
            return ResponseVo.errorByMsg(CodeMsg.ADDRESS_NUM_EXCEED_LIMIT);
        }
        //把地址信息添加到数据库
        if(addressMapper.insertSelective(address) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ADDRESS_ADD_ERROR);
        }
        return ResponseVo.successByMsg(true, "添加地址成功！");
    }

    @Override
    public List<Address> findAddressByUserId(Long userId) {
        return addressMapper.findAddressByUserId(userId);
    }

    @Override
    public ResponseVo<Boolean> setFirstSelected(Long id, HttpServletRequest request) {
        if(id == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        String userId = (String) request.getAttribute("id");
        //先把当前用户所有地址都设置为不是首选
        if(addressMapper.updateFirstSelectedByUserId(Long.valueOf(userId), AddressFirstSelectedEnum.NO.getCode()) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ADDRESS_SET_FIRST_SELECTED_ERROR);
        }
        //根据要设置为订单首选的地址id从数据库中获取地址信息
        Address address = addressMapper.selectByPrimaryKey(id);
        address.setFirstSelected(AddressFirstSelectedEnum.YES.getCode());
        //更新数据库中的信息
        if(addressMapper.updateByPrimaryKeySelective(address) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ADDRESS_SET_FIRST_SELECTED_ERROR);
        }
        return ResponseVo.successByMsg(true, "成功设置该地址为订单首选！");
    }

    @Override
    public ResponseVo<Boolean> delete(Long id) {
        if(id == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(addressMapper.deleteByPrimaryKey(id) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ADDRESS_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "删除地址成功！");
    }

    @Override
    public Address selectByUserIdAndFirstSelected(Long userId, Integer firstSelected) {
        return addressMapper.selectByUserIdAndFirstSelected(userId, firstSelected);
    }
}
