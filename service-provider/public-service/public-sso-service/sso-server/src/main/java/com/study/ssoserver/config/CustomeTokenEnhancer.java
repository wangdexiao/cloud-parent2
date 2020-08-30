package com.study.ssoserver.config;

import com.study.ssoserver.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomeTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        Authentication userAuthentication = authentication.getUserAuthentication();
        Object credentials = authentication.getCredentials();

        Map<String, Object> info = new HashMap<>();
        // 需要增加的信息
        // 所以如果是需要动态的话，只能在该方法中去调用业务方法添加动态参数信息
        info.put("account", userInfo.getUsername());
        info.put("name", userInfo.getNickName());
        info.put("mobile", userInfo.getMobile());
        info.put("role", userInfo.getRole());
        // 设置附加信息
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
