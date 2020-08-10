package com.study.contentmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan(basePackages = "com.study.contentmanage.config")
@MapperScan("com.study.contentmanage.mapper")
@EnableConfigurationProperties
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class ContentManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentManageApplication.class, args);
    }
}
