package com.study.ribbonconsumer.controller;

import com.study.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    //方式一
//    @Autowired
//    RestTemplate restTemplate;
//
//    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
//    public String helloConsumer(){
//        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
//    }

    //方式二
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.helloService();
    }


}
