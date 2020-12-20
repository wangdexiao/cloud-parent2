package com.study.feignconsumer.controller;

import com.study.feignconsumer.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ConsumerController {


    @Autowired
    private HelloService helloService;

//    @Autowired
//    private HelloService3 helloService32;

    @Value("${testnumber}")
    private int testNum;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        log.error("testNum:" + testNum);

    }


//    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
//    public String helloConsumer2(){
//        return helloService32.hello();
//    }
}
