package com.study.ribbonconsumer.controller;

import com.study.ribbonconsumer.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RefreshScope
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

    @Value("${testNum}")
    private int testNum;

    //方式二/hello
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        log.error("testNum:" + testNum);
        return helloService.helloService();
    }

    @PostMapping(value = "/testNum")
    public String testNum(){
        log.error("testNum:" + testNum);
        return testNum + "";
    }

}
