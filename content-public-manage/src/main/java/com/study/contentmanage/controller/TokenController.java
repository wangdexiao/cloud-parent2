package com.study.contentmanage.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TokenController {

    @Autowired
    private TokenStore tokenStore;


    @GetMapping("/getToken")
    public Collection<OAuth2AccessToken> getToken(){
        Collection<OAuth2AccessToken> sharececuo = tokenStore.findTokensByClientId("sharececuo");
        return sharececuo;
    }
}
