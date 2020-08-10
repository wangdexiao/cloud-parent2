package com.study.serviceprovider.controller;

import com.study.serviceprovider.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private TokenStore tokenStore;


    @PostMapping("/userInfo")
    @PreAuthorize("isAuthenticated()")
    public UserInfoEntity authentication(@RequestParam("access_token") String token){
        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(token);
        Authentication userAuthentication = auth2Authentication.getUserAuthentication();

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserName((String) userAuthentication.getPrincipal());
        List<String> authories = new ArrayList<>();
        userAuthentication.getAuthorities().forEach(it -> authories.add(it.getAuthority()));
        userInfoEntity.setAuthories(authories);
        userInfoEntity.setRoles(new ArrayList<>());
        return userInfoEntity;
    }

}
