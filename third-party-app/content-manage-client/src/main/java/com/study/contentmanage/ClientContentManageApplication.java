package com.study.contentmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.study.contentmanage.config")
@MapperScan("com.study.contentmanage.mapper")
@EnableConfigurationProperties
//@EnableEurekaClient
//@EnableCircuitBreaker
@SpringBootApplication
public class ClientContentManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientContentManageApplication.class, args);
    }
}
