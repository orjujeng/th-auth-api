package com.orjujeng.auth;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients(basePackages = "com.orjujeng.auth.feign")
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.orjujeng.auth.mapper")
public class ThAuthApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThAuthApiApplication.class, args);
	}

}
