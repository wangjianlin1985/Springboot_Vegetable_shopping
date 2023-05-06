package com.yjq.programmer.service.home;

import com.yjq.programmer.pojo.home.Address;
import com.yjq.programmer.vo.common.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-11 12:54
 */

/**
 * 地址service接口
 * @author 82320
 *
 */
public interface IAddressService {

    //添加地址操作处理
    ResponseVo<Boolean> add(Address address, HttpServletRequest request);

    //根据用户id查找地址
    List<Address> findAddressByUserId(Long userId);

    //设置订单首选地址操作处理
    ResponseVo<Boolean> setFirstSelected(Long id, HttpServletRequest request);

    //删除地址操作处理
    ResponseVo<Boolean> delete(Long id);

    //根据用户id和地址是否首选来获取地址
    Address selectByUserIdAndFirstSelected(Long userId, Integer firstSelected);
}
