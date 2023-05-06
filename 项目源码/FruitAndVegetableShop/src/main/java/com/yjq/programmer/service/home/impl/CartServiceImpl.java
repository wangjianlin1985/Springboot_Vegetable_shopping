package com.yjq.programmer.service.home.impl;

import com.google.gson.Gson;
import com.yjq.programmer.bean.CodeMsg;
import com.yjq.programmer.dao.common.ProductMapper;
import com.yjq.programmer.pojo.common.Product;
import com.yjq.programmer.pojo.home.Cart;
import com.yjq.programmer.service.home.ICartService;
import com.yjq.programmer.util.StringUtil;
import com.yjq.programmer.util.ValidateEntityUtil;
import com.yjq.programmer.vo.common.ResponseVo;
import com.yjq.programmer.vo.home.CartProductVo;
import com.yjq.programmer.vo.home.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-15 19:54
 */

/**
 * 购物车service接口实现类
 * @author 82320
 *
 */
@Service
public class CartServiceImpl implements ICartService {

    //redis键名模板
    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductMapper productMapper;

    private Gson gson = new Gson();


    @Override
    public ResponseVo<Boolean> add(Long uid, Cart addCart) {
        if(addCart == null){
            return ResponseVo.errorByMsg(CodeMsg.DATA_ERROR);
        }
        if(addCart.getQuantity() == null){
            addCart.setQuantity(1);
        }
        //统一表单验证
        CodeMsg validate = ValidateEntityUtil.validate(addCart);
        if(!validate.getCode().equals(CodeMsg.SUCCESS.getCode())) {
            return ResponseVo.errorByMsg(validate);
        }
        Product product = productMapper.selectByPrimaryKey(addCart.getProductId());
        //商品是否存在
        if(product == null){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_NOT_EXIST);
        }
        //商品库存是否充足
        if (addCart.getQuantity() > product.getStock()) {
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_STOCK_ERROR);
        }
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        Cart cart;
        //value：在redisKey(cart_1)中查找Map表中键名为product.getId()的对应的值
        String value = opsForHash.get(redisKey, String.valueOf(product.getId()));
        if(StringUtil.isEmpty(value)){
            //没有该商品, 新增
            cart = new Cart(product.getId(), addCart.getQuantity());
        }else{
            //已经有了，数量+1
            //反序列化：json格式转化为其他格式，这里json格式的value转化为Cart实体类
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + addCart.getQuantity());
        }
        opsForHash.put(redisKey,
                String.valueOf(product.getId()),
                gson.toJson(cart));
        return ResponseVo.successByMsg(true, "添加购物车成功！");
    }

    @Override
    public ResponseVo<CartVo> list(Long uid) {
        List<CartProductVo> cartProductVoList = new ArrayList<>(); //购物车所有商品的列表
        BigDecimal cartTotalPrice = BigDecimal.ZERO; //购物车总价
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //获取指定redisKey的所有商品
        Map<String, String> entries = opsForHash.entries(redisKey);
        Set<Long> productIdSet = new HashSet<>(); //存放购物车所有商品的id
        CartProductVo cartProductVo;
        //遍历所有商品
        for(Map.Entry<String,String> entry : entries.entrySet()){
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            productIdSet.add(cart.getProductId()); //购物车每个商品的id添加到Set中
            cartProductVo = new CartProductVo(cart.getProductId(), cart.getQuantity());
            cartProductVoList.add(cartProductVo);
        }
        //根据商品id集合去数据库中获取商品信息
        List<Product> productList = productMapper.selectByProductIdSet(productIdSet);
        for(CartProductVo cartProduct : cartProductVoList){
            for(Product product : productList){
                if(cartProduct.getProductId().equals(product.getId())){
                    cartProduct.setPrice(product.getPrice());
                    cartProduct.setProductName(product.getProductName());
                    cartProduct.setProductPic(product.getProductPic());
                    cartProduct.setProductTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())));
                    cartTotalPrice = cartTotalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(cartProduct.getQuantity())));
                }
            }
        }
        CartVo cartVo = new CartVo();
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setCartTotalPrice(cartTotalPrice);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<Boolean> update(Long uid, Long productId, String method) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Product product = productMapper.selectByPrimaryKey(productId);
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        //判断商品是否存在
        if(StringUtil.isEmpty(value)){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_NOT_EXIST);
        }
        Cart cart = gson.fromJson(value, Cart.class);;
        if("add".equals(method)){
            //添加商品数量操作
            //判断库存是否足够
            if(cart.getQuantity() >= product.getStock()){
                return ResponseVo.errorByMsg(CodeMsg.PRODUCT_STOCK_ERROR);
            }
            //商品数量+1
            if(cart.getQuantity() != null && cart.getQuantity() >= 0){
                cart.setQuantity(cart.getQuantity() + 1);
            }
        }else if("reduce".equals(method)){
            //减少商品数量操作
            //商品数量-1
            if(cart.getQuantity() != null && cart.getQuantity() >= 0){
                cart.setQuantity(cart.getQuantity() - 1);
            }
        }
        opsForHash.put(redisKey, String.valueOf(productId), gson.toJson(cart));
        return ResponseVo.success(true);
    }

    @Override
    public ResponseVo<Boolean> delete(Long uid, Long productId) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        //判断商品是否存在
        if(StringUtil.isEmpty(value)){
            return ResponseVo.errorByMsg(CodeMsg.PRODUCT_NOT_EXIST);
        }
        //删除商品
        opsForHash.delete(redisKey, String.valueOf(productId));
        return ResponseVo.success(true);
    }

    @Override
    public ResponseVo<Integer> total(Long uid) {
        Integer total = 0;
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        //遍历所有商品
        for(Map.Entry<String,String> entry : entries.entrySet()){
            total++;
        }
        return ResponseVo.success(total);
    }
}
