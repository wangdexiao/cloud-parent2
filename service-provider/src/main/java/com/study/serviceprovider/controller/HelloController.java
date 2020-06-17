package com.study.serviceprovider.controller;

import com.study.serviceprovider.listener.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServerConfig serverConfig;


    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String index(){
        List<String> services = discoveryClient.getServices();
        log.info("services:" + services.toString());

        return serverConfig.getPort() + "Hello World";
    }
}
