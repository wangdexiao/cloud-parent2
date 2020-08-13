package com.study.ssoserver.config;

import com.netflix.discovery.converters.Auto;
import com.study.ssoserver.filter.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class CorsConfig {

    @Autowired
    private CORSFilter corsFilter;
    @Bean
    public FilterRegistrationBean<CORSFilter> corsFilterFilterRegistrationBean(){
        FilterRegistrationBean<CORSFilter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterFilterRegistrationBean.setFilter(corsFilter);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setName("CORSFilter");
        return filterFilterRegistrationBean;
    }
}
