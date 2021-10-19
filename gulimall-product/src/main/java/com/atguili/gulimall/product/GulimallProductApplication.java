package com.atguili.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、整合 mybatis-plus
 * 2、配置 mysql 数据源
 * 3、配置 mybatis-plus
 *
 * 4、后端校验 javax.validation.constraints
 *
 */
@MapperScan("com.atguili.gulimall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallProductApplication.class, args);
	}

}
