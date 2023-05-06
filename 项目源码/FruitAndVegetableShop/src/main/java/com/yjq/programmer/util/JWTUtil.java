package com.yjq.programmer.util;

/**
 * @author 杨杨吖
 * @QQ 823208782
 * @WX yjqi12345678
 * @create 2020-11-09 19:31
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;

/**
 * JWT认证协议工具类
 */
public class JWTUtil {

    //定义签名
    private static final String SIGN = "yang yang ya";

    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7); //默认7天过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{builder.withClaim(k,v);});

        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
        log.info("生成的token={}",token);

        return token;
    }

    /**
     * 验证token合法性并返回token基本信息
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }


}
