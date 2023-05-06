package com.yjq.programmer.dao.common;

import com.yjq.programmer.pojo.common.Order;
import com.yjq.programmer.pojo.common.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单mapper接口
 */
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    //根据订单id和用户id查询订单
    Order selectByOrderIdAndUserId(@Param("userId") Long userId, @Param("orderId") Long orderId);

    //根据用户id和用户是否删除订单来查询订单
    List<Order> selectByUserIdAndIsDeleted(@Param("userId") Long userId, @Param("isDeleted") Integer isDeleted);

    //根据订单状态和用户id和用户是否删除订单来查询订单
    List<Order> selectByOrderStateAndUserIdAndIsDeleted(@Param("state") Integer state, @Param("userId") Long userId, @Param("isDeleted") Integer isDeleted);

    //根据订单id查询订单详情
    List<OrderItem> getOrderItemByOrderId(@Param("orderId") Long orderId);

    //根据订单id修改订单状态
    int updateStateByOrderId(@Param("orderId") Long orderId, @Param("state") Integer state);

    //根据订单id修改用户是否删除订单
    int updateIsDeletedByOrderId(@Param("orderId") Long orderId, @Param("isDeleted") Integer isDeleted);

    //获取所有订单
    List<Order> selectAll();

    //根据搜索内容获取订单
    List<Order> selectBySearchContent(Long orderNo);

    //获取今日所有订单
    List<Order> getOrderByDay();

    //获取本周所有订单
    List<Order> getOrderByWeek();

    //获取本月所有订单
    List<Order> getOrderByMonth();

}