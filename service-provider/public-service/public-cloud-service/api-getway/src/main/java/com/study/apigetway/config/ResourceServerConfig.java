package com.study.apigetway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 请求头要传 Authorization: Bearer 后边就跟正确的token
 * ,要不就别传,传过来就会校验,放行的请求也不能通过
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    public static final String RESOURCE_ID = "api-getway";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //认证服务器的相关资源全部放行,用于处理认证
        http.authorizeRequests()
                .antMatchers("/login", //sso登录(传入授权码,获取token)
                        "/sso-server/**", //认证服务器的所有请求全部放行
                        "/content-manage/ueditor/**"
//                        "/content-manage/content-preview/**"
                ).permitAll()
                .anyRequest().authenticated();
    }
}