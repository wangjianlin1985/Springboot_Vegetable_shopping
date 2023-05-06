package com.yjq.programmer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目入口启动文件
 *
 */
@SpringBootApplication
@MapperScan({"com.yjq.programmer.dao.admin","com.yjq.programmer.dao.home","com.yjq.programmer.dao.common"})
public class FruitAndVegetableShop
{
    public static void main( String[] args )
    {
        SpringApplication.run(FruitAndVegetableShop.class, args);
    }
}
