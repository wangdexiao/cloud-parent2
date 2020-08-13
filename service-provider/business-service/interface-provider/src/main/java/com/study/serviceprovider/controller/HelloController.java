package com.study.serviceprovider.controller;

import com.study.serviceprovider.listener.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String index(HttpServletRequest request, HttpServletResponse response){

        log.error("testNum:" + testNum);
        List<String> services = discoveryClient.getServices();
        log.info("services:" + services.toString());

        return ipAddress + ":" + port + "应用:" + "Hello World";
    }

    @PostMapping(path = "/hello2")
    public String index(String username){

        log.error("username:" + username);

        return username;
    }

    @PreAuthorize("hasAnyAuthority('sys:manage')")
    @RequestMapping(path = "/hellores",method = RequestMethod.POST)
    public String res1(){
        return "我是来自 service-provider 资源服务器的资源1";
    }


    @PreAuthorize("hasAnyAuthority('sys:nomatch')")
    @RequestMapping(path = "/hellores2",method = RequestMethod.POST)
    public String res2(){
        return "我是来自 service-provider 资源服务器的资源2";
    }
}
