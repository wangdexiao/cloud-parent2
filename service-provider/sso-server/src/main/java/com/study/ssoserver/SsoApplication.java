package com.study.ssoserver;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan(basePackages = "com.study.ssoserver.filter")
@EnableEurekaClient
@MapperScan("com.study.ssoserver.mapper")
@SpringBootApplication(scanBasePackages = {"com.study.ssoserver","com.study.base"})
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }
}
