package com.study.feignconsumer.service.fallback;

import com.study.feignconsumer.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements HelloService {

    @Override
    public String hello() {
        return "error";
    }
}
