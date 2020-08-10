package com.study.apigetway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableResourceServer
public class ResourceServerConfig {


    public static final String RESOURCE_ID = "product-server";

    @Autowired
    private TokenStore tokenStore;

    @Configuration
    @EnableResourceServer
    public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter{

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            //认证服务器的相关资源全部放行,用于处理认证
            http.authorizeRequests().anyRequest().permitAll();
        }
    }

}
