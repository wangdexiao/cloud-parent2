package com.study.contentmanage.controller;

import com.study.base.mybatisplus.entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("")
public class LoginController {


    @Value("${sso.client-id:}")
    private String clientId;

    @Value("${sso.client-secret:}")
    private String clientSecret;

    @Value("${sso.redirect-uri:}")
    private String redirectUri;

    @Value("${sso.access-token-uri:}")
    private String accessTokenUri;

    @Value("${sso.user-info-uri:}")
    private String userInfoUri;

    @PostMapping("/login")
    public Result tokenInfo( String code) throws UnsupportedEncodingException {
        //获取token
        Result<Map> tokenMapResult = getAccessToken(code);
        if(tokenMapResult.getCode() == Result.OK){
            Map tokenMap = tokenMapResult.getData();
            String accessToken = (String)tokenMap.get("access_token");
            //获取用户信息
            Map userInfo = getUserInfo(accessToken);
//        List<String> roles = getRoles(userInfo);

            Map<String,Object> result = new HashMap<String,Object>(3);
            result.put("tokenInfo", tokenMap);
            result.put("userInfo", userInfo);
            result.put("roles", userInfo.get("authories"));

            return Result.ok(result);
        }else {
            return tokenMapResult;
        }

    }

    /**
     * 获取token
     */
    public Result<Map> getAccessToken(String code) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        byte[] authorization = (clientId + ":" + clientSecret).getBytes("UTF-8");
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Auth = encoder.encode(authorization);
        headers.add("Authorization", "Basic " + base64Auth);

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("code", code);
        param.add("grant_type", "authorization_code");
        param.add("redirect_uri", redirectUri);
        param.add("scope", "all");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(accessTokenUri, request , Map.class);
        Map result = response.getBody();
        return Result.<Map>ok(result);
    }

    /**
     * 获取用户信息
     */
    public Map getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        Map result = restTemplate.postForObject(userInfoUri+"?access_token="+accessToken,null,Map.class);
        return result;
    }

}
