package com.study.ssoserver.config;

import com.study.ssoserver.filter.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

//    @Autowired
//    private LogFilter logFilter;
//
   @Autowired
    private CORSFilter corsFilter;

//    @Bean
//    public FilterRegistrationBean<LogFilter> corsFilterFilterRegistrationBean(){
//        FilterRegistrationBean<LogFilter> filterFilterRegistrationBean
//                = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        filterFilterRegistrationBean.setFilter(logFilter);
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.setName("LogFilter");
//        return filterFilterRegistrationBean;
//    }

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
