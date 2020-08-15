package com.study.contentmanage.config;

import com.study.contentmanage.filter.LogFilter;
//import com.study.contentmanage.filter.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Autowired
    private LogFilter logFilter;

//    @Autowired
//    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Bean
    public FilterRegistrationBean<LogFilter> corsFilterFilterRegistrationBean(){
        FilterRegistrationBean<LogFilter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterFilterRegistrationBean.setFilter(logFilter);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setName("LogFilter");
        return filterFilterRegistrationBean;
    }


//    @Bean
//    public FilterRegistrationBean<TokenAuthenticationFilter> tokenAuthenticationFilterFilterRegistrationBean(){
//        FilterRegistrationBean<TokenAuthenticationFilter> filterFilterRegistrationBean
//                = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE - 1);
//        filterFilterRegistrationBean.setFilter(tokenAuthenticationFilter);
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.setName("TokenAuthenticationFilter");
//        return filterFilterRegistrationBean;
//    }
}
