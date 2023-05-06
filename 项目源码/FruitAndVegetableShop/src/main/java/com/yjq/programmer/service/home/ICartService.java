package com.yjq.programmer.service.home;

import com.yjq.programmer.pojo.home.Cart;
import com.yjq.programmer.vo.common.ResponseVo;
import com.yjq.programmer.vo.home.CartVo;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-15 19:54
 */

/**
 * 购物车service接口
 * @author 82320
 *
 */
public interface ICartService {

    //添加购物车
    ResponseVo<Boolean> add(Long uid, Cart addCart);

    //显示购物车
    ResponseVo<CartVo> list(Long uid);

    //购物车商品数量变更
    ResponseVo<Boolean> update(Long uid, Long productId, String method);

    //删除购物车商品操作处理
    ResponseVo<Boolean> delete(Long uid, Long productId);

    //统计购物车中有几种商品
    ResponseVo<Integer> total(Long uid);

}
