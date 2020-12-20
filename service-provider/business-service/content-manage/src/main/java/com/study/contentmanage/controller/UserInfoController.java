package com.study.contentmanage.controller;

import com.study.base.mybatisplus.entity.Result;
import com.study.base.mybatisplus.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserInfoController {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;


    @PostMapping("/getUserInfo")
    public Result getUserInfo(){
        OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
        String value = accessToken.getValue();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoEntity userInfo = (UserInfoEntity)authentication.getPrincipal();
        return Result.ok(userInfo);
    }


    @PostMapping("/hellores")
    public String helloRes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object credentials = authentication.getCredentials();
        String forEntity = oAuth2RestTemplate.postForObject("http://192.168.1.100:8002/hellores",null, String.class, (Object) null);
        return forEntity;
    }
}
