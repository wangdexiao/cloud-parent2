package com.study.contentmanage.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Slf4j
@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

    @Value("${spring.application.name}")
    private String resourceId;

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .resourceId(resourceId);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                        .anyRequest().authenticated();
    }
    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
////                .antMatcher("/**")//允许将{@link HttpSecurity}配置为仅在与提供的 （antMatcher）蚂蚁图案。 如果需要更高级的配置，请考虑使用{@link #requestMatchers（）}或{@link #requestMatcher（RequestMatcher）}。
//                .authorizeRequests() //authorize:授权 允许使用{@link RequestMatcher}实现（例如通过URL模式）基于{@link HttpServletRequest}限制访问。
//                .antMatchers("/ueditor/config","/logout","/test","/clients/**")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .successHandler(new AjaxAuthSuccessHandler())
//                    .failureHandler(new AjaxAuthFailHandler())
//                .and()
//                    .logout().logoutSuccessUrl("http://192.168.1.100:8000/logout");
//        http.csrf().disable();
//
//
//    }



}
