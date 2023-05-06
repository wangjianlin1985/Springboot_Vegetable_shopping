package com.yjq.programmer.service.common.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.common.OrderItemMapper;
import com.yjq.programmer.dao.common.OrderMapper;
import com.yjq.programmer.dao.common.ProductMapper;
import com.yjq.programmer.dao.home.AddressMapper;
import com.yjq.programmer.enums.AddressFirstSelectedEnum;
import com.yjq.programmer.enums.MailTypeEnum;
import com.yjq.programmer.enums.OrderStateEnum;
import com.yjq.programmer.pojo.common.Order;
import com.yjq.programmer.pojo.common.OrderItem;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.pojo.home.Address;
import com.yjq.programmer.pojo.home.Cart;
import com.yjq.programmer.service.common.IOrderService;
import com.yjq.programmer.util.MailUtil;
import com.yjq.programmer.util.SnowFlake;
import com.yjq.programmer.util.StringUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-16 19:37
 */

/**
 * 用户service接口实现类
 * @author 82320
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    //redis键名模板
    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    private Gson gson = new Gson();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public ResponseVo<Long> generate(String ids, HttpServletRequest request) {
        if(StringUtil.isEmpty(ids)){
            CodeMsg codeMsg = CodeMsg.DATA_ERROR;
            codeMsg.setMsg("请勾选你要购买的商品！");
            return ResponseVo.errorByMsg(codeMsg);
        }
        String[] split = ids.split(",");
        Set<Long> productIdSet = new HashSet<>();
        for(String id : split){
            productIdSet.add(Long.valueOf(id));
        }
        //获取选中商品的详细信息
        List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
        //声明一个OrderItem类型的List和OrderItem对象
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem;
        //从redis中获取这些选中商品的数据
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        //获取当前登录用户的id
        String uid = (String) request.getAttribute("id");
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, Long.valueOf(uid));
        //获取指定redisKey的所有商品
        Map<String, String> entries = opsForHash.entries(redisKey);
        //订单总价
        BigDecimal orderTotalPrice = BigDecimal.ZERO;
        //遍历购物车所有商品,把选中的添加到orderItemList中
        for(Map.Entry<String,String> entry : entries.entrySet()){
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            for(Product product : productList){
                if(product.getId().equals(cart.getProductId())){
                    orderItem = new OrderItem(product.getId(), cart.getQuantity(),
                            product.getProductName(),
                            product.getProductPic(),
                            product.getPrice(),
                            product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
                    orderItemList.add(orderItem);
                    orderTotalPrice = orderTotalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
                }
            }
        }
        //声明一个订单对象
        Order order = new Order(new SnowFlake(2,3).nextId(),
                Long.valueOf(uid),
                OrderStateEnum.NO_PAY.getCode(),
                orderTotalPrice);
        //把订单信息添加到数据库中
        if(orderMapper.insertSelective(order) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_ADD_ERROR);
        }
        //给订单详情对应的订单id赋值
        for(OrderItem or : orderItemList){
            or.setOrderId(order.getId());
        }
        //把订单详情信息添加到数据库中
        if(orderItemMapper.batchInsert(orderItemList) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_ITEM_ADD_ERROR);
        }
        return ResponseVo.success(order.getId());
    }

    @Override
    public Order selectByOrderIdAndUserId(Long userId, Long orderId) {
        return orderMapper.selectByOrderIdAndUserId(userId, orderId);
    }

    @Override
    @Transactional
    public ResponseVo<Boolean> submit(String remark, Long orderId, Long uid, String email) {
        //判断订单是否存在
        Order order = orderMapper.selectByOrderIdAndUserId(uid, orderId);
        if(order == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //判断配送地址是否选择
        Address address = addressMapper.selectByUserIdAndFirstSelected(uid, AddressFirstSelectedEnum.YES.getCode());
        if(address == null){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_ADDRESS_EMPTY);
        }
        //判断订单留言长度是否合法
        if(remark.length() > 50){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_REMARK_EXCEED_LENGTH);
        }
        //声明一个自定义错误的CodeMsg
        CodeMsg codeMsg = CodeMsg.ORDER_ERROR;
        //声明一个商品id的Set集合
        Set<Long> productIdSet = new HashSet<>();
        //遍历订单中的商品
        for(OrderItem orderItem : order.getOrderItemList()){
            productIdSet.add(orderItem.getProductId());
        }
        //获取这些选中商品的详细信息
        List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
        //分别比较订单中商品购买数量是否大于库存
        for(OrderItem orderItem : order.getOrderItemList()){
            for(Product product : productList){
                if(orderItem.getProductId().equals(product.getId())){
                    if(orderItem.getQuantity() > product.getStock()){
                        codeMsg.setMsg("商品<"+product.getProductName()+">库存不足了，请减少购买数量！");
                        return ResponseVo.errorByMsg(codeMsg);
                    }
                }
            }
        }
        //减少商品库存和增加商品销量
        for(OrderItem orderItem : order.getOrderItemList()){
            for(Product product : productList){
                if(orderItem.getProductId().equals(product.getId())){
                   product.setStock(product.getStock() - orderItem.getQuantity());
                   product.setSellNum(product.getSellNum() + orderItem.getQuantity());
                }
            }
        }
        //修改数据库中商品库存和商品销量信息
        for(Product product : productList){
            if(productMapper.updateByPrimaryKeySelective(product) <= 0){
                throw new RuntimeException("商品库存信息更新失败！");
            }
        }
        //修改订单信息
        order.setAddressId(address.getId());
        order.setRemark(remark);
        order.setState(OrderStateEnum.PAYED.getCode());
        //修改数据库中的订单信息
        if(orderMapper.updateByPrimaryKeySelective(order) <= 0){
            throw new RuntimeException("订单提交失败！");
        }
        //清除购物车中选中的商品（redis有事务，但不能回滚，放最后处理）
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, Long.valueOf(uid));
        for(OrderItem orderItem : order.getOrderItemList()){
            String value = opsForHash.get(redisKey, String.valueOf(orderItem.getProductId()));
            //删除商品
            opsForHash.delete(redisKey, String.valueOf(orderItem.getProductId()));
        }
        //发送邮件提醒用户订单提交成功
        MailUtil.sendMail(MailTypeEnum.ORDER_SUBMIT.getCode(), email, String.valueOf(order.getOrderNo()));
        return ResponseVo.successByMsg(true, "订单提交成功,交易完成！");
    }

    @Override
    public ResponseVo<PageInfo> selectByPageAndUserIdAndIsDeleted(Long userId, Integer isDeleted, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectByUserIdAndIsDeleted(userId, isDeleted);
        PageInfo pageInfo = new PageInfo<>(orderList);
        pageInfo.setList(orderList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public List<Order> selectByOrderStateAndUserIdAndIsDeleted(Integer state, Long userId, Integer isDeleted) {
        return orderMapper.selectByOrderStateAndUserIdAndIsDeleted(state, userId, isDeleted);
    }

    @Override
    public ResponseVo<Boolean> updateOrderState(Long orderId, Integer state) {
        if(orderId == null || state == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_NOT_EXIST);
        }
        if(orderMapper.updateStateByOrderId(orderId, state) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_STATE_EDIT_ERROR);
        }
        return ResponseVo.successByMsg(true, "成功修改订单状态！");
    }

    @Override
    public ResponseVo<Boolean> userDelete(Long orderId, Integer isDeleted) {
        if(orderId == null || isDeleted == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_NOT_EXIST);
        }
        if(orderMapper.updateIsDeletedByOrderId(orderId, isDeleted) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "成功删除该订单！");
    }

    @Override
    public ResponseVo<PageInfo> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(orderList);
        pageInfo.setList(orderList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<PageInfo> selectByPageAndContent(Long orderNo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectBySearchContent(orderNo);
        PageInfo pageInfo = new PageInfo<>(orderList);
        pageInfo.setList(orderList);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public Order selectByPrimaryKey(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(Long orderId) {
        return orderMapper.getOrderItemByOrderId(orderId);
    }

    @Override
    public ResponseVo<Boolean> deleteOrder(Long orderId) {
        if(orderId == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        //从数据库中删除订单信息
        if(orderMapper.deleteByPrimaryKey(orderId) <= 0){
            return ResponseVo.errorByMsg(CodeMsg.ORDER_DELETE_ERROR);
        }
        return ResponseVo.successByMsg(true, "订单删除成功！");
    }

    @Override
    public List<Order> getOrderByDay() {
        return orderMapper.getOrderByDay();
    }

    @Override
    public List<Order> getOrderByWeek() {
        return orderMapper.getOrderByWeek();
    }

    @Override
    public List<Order> getOrderByMonth() {
        return orderMapper.getOrderByMonth();
    }

}
