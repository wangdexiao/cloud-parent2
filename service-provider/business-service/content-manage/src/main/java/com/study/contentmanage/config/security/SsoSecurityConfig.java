package com.study.contentmanage.config.security;

import com.study.contentmanage.security.SsoSecurityConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Configuration
@EnableOAuth2Sso
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别权限控制
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {


//    private final ApplicationContext applicationContext;
//
//    public SsoSecurityConfig(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    @Autowired
    private SsoSecurityConfigurer ssoSecurityConfigurer;

    @Autowired
    JwtAccessTokenConverter jwtTokenEnhancer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ssoSecurityConfigurer.configure(http);
        http.
                authorizeRequests()
                // 首页所有人都可以访问
                .antMatchers("/ueditor/**").permitAll()
                .antMatchers("/content-preview/**","/content/query/**").permitAll()
                //其他要认证后才可以访问，如 /member
                .anyRequest().authenticated()
                .and()
                .logout()
                //当前应用退出后，会交给某个处理
                // 请求认证服务器将用户进行退出
                .logoutSuccessUrl("http://192.168.1.100:8000/logout")
                .and()
                .csrf().disable()
        ;

    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }




}
