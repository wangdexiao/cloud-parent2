//package com.study.contentmanage.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//
//
//@Configuration
//public class Oauth2ClientConfig {
//
//    @Autowired
//    private OAuth2ClientContext oauth2Context;
//
//    @Autowired
//    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;
//
//    @Bean
//    public OAuth2RestTemplate sparklrRestTemplate() {
//        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oauth2Context);
//    }
//
//}
//
