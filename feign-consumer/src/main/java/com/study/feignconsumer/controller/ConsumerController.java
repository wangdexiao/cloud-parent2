package com.study.feignconsumer.controller;

import com.study.feignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {


    @Autowired
    private HelloService helloService3;

//    @Autowired
//    private HelloService3 helloService32;


    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService3.hello();
    }


//    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
//    public String helloConsumer2(){
//        return helloService32.hello();
//    }
}
