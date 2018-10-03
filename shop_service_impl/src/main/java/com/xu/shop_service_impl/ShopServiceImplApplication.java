package com.xu.shop_service_impl;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan("com.xu.shop_service_impl.service.impl")
@MapperScan("com.xu.dao")
public class ShopServiceImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopServiceImplApplication.class, args);
	}
}
