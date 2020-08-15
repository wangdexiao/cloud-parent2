//package com.study;
//
//import com.google.common.collect.Sets;
//import com.study.contentmanage.ContentManageApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Request;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ContentManageApplication.class)
//public class test {
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Test
//    public void testTokenStore(){
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("sys:admin"));
//
//        Map<String, String> map = new HashMap<>();
//        map.put("test", "test");
//        HashSet<String> role_admin = Sets.newHashSet("role_admin");
//        HashSet<String> res_id = Sets.newHashSet("res_id");
//        OAuth2Request oAuth2Request = new OAuth2Request(map, "sharecuosuo", grantedAuthorities, true, role_admin, res_id, "http://www.baidu.com", Sets.newHashSet("test"), null);
//
//
//        Authentication userAuth = new UsernamePasswordAuthenticationToken("wadexi", null, grantedAuthorities);
//        OAuth2Authentication auth2Authentication = new OAuth2Authentication(oAuth2Request,userAuth);
//        OAuth2AccessToken token = new DefaultOAuth2AccessToken("12345678901234567890123456789012");
//        tokenStore.storeAccessToken(token,auth2Authentication);
//        OAuth2AccessToken accessToken = tokenStore.getAccessToken(auth2Authentication);
//    }
//}
