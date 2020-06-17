package com.study.feignconsumer.service;

import com.study.feignconsumer.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "HELLO-SERVICE",fallback = HelloServiceFallback.class)
public interface HelloService2 {


    @RequestMapping("/hello")
    String hello();
}
