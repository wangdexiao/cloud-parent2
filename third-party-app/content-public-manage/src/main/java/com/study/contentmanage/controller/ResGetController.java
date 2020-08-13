package com.study.contentmanage.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResGetController {

//    @Autowired
//    private OAuth2RestTemplate oAuth2RestTemplate;
//
//    @PostMapping("/getres1")
//    public String reqServiceProviderRes1(){
//        OAuth2ProtectedResourceDetails resource = oAuth2RestTemplate.getResource();
//        OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
//        String value = accessToken.getValue();
//        return oAuth2RestTemplate.postForObject("http://192.168.1.100/hellores", null, String.class);
//    }
}
