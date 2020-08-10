package com.study.apigetway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;

@ServletComponentScan(basePackages = "com.study.apigetway.filter")
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public interface ApiGetwayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApiGetwayApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
