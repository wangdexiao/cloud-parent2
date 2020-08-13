package com.study.ssoserver.controller;

import com.study.ssoserver.mapper.TestMapper;
import com.study.ssoserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestController {

    @Resource
    TestMapper testMapper;

    @Resource
    UserMapper userMapper;

//    @Resource
//    private ClientMapper clientMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @GetMapping("/test")
    public ClientDetails count(){
        ClientDetails sharecesuo = clientDetailsService.loadClientByClientId("sharecesuo");
        log.info(sharecesuo.toString());
//        return testMapper.selectCount();
//        UserInfo admin = userMapper.loadUserByUsername("admin");
//        ClientInfo clientInfo = clientMapper.loadClientByClientId("client_id");
        return sharecesuo;
    }
}
