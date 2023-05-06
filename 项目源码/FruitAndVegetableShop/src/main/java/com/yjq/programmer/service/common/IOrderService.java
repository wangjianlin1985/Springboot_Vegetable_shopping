package com.yjq.programmer.service.common;

import com.github.pagehelper.PageInfo;
import com.yjq.programmer.pojo.common.Order;
import com.yjq.programmer.pojo.common.OrderItem;
import com.yjq.programmer.vo.common.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-16 19:36
 */

/**
 * 订单service接口
 * @author 82320
 *
 */
public interface IOrderService {

    //订单生成操作处理
    ResponseVo<Long> generate(String ids, HttpServletRequest request);

    //根据订单id和用户id查询订单
    Order selectByOrderIdAndUserId(Long userId, Long orderId);

    //订单提交操作处理
    ResponseVo<Boolean> submit(String remark, Long orderId, Long uid, String email);

    //根据分页和用户id和用户是否删除订单来查询订单
    ResponseVo<PageInfo> selectByPageAndUserIdAndIsDeleted(Long userId, Integer isDeleted, Integer pageNum, Integer pageSize);

    //根据订单状态和用户id和用户是否删除订单来查询订单
    List<Order> selectByOrderStateAndUserIdAndIsDeleted(Integer state, Long userId, Integer isDeleted);

    //修改订单状态操作处理
    ResponseVo<Boolean> updateOrderState(Long orderId, Integer state);

    //修改用户是否删除订单操作处理
    ResponseVo<Boolean> userDelete(Long orderId, Integer isDeleted);

    //根据分页获取订单
    ResponseVo<PageInfo> selectByPage(Integer pageNum, Integer pageSize);

    //根据分页和搜索内容获取订单
    ResponseVo<PageInfo> selectByPageAndContent(Long orderNo, Integer pageNum, Integer pageSize);

    //根据订单id查询订单
    Order selectByPrimaryKey(Long id);

    //根据订单id查询订单详情
    List<OrderItem> getOrderItemByOrderId(Long orderId);

    //删除订单操作处理
    ResponseVo<Boolean> deleteOrder(Long orderId);

    //获取今日所有订单
    List<Order> getOrderByDay();

    //获取本周所有订单
    List<Order> getOrderByWeek();

    //获取本月所有订单
    List<Order> getOrderByMonth();

}
