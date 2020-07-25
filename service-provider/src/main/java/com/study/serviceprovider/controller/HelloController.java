package com.study.serviceprovider.controller;

import com.study.serviceprovider.listener.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.cloud.client.ip-address}")
    private String ipAddress;
    @Value("${server.port}")
    private int port;

    @Value("${testNum}")
    private int testNum;


    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String index(){

        log.error("testNum:" + testNum);
        List<String> services = discoveryClient.getServices();
        log.info("services:" + services.toString());

        return ipAddress + ":" + port + "应用:" + "Hello World";
    }
}
