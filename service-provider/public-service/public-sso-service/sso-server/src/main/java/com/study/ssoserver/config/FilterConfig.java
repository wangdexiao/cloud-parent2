package com.study.ssoserver.config;

import com.study.ssoserver.filter.LogFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Autowired
    private LogFilter logFilter;

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
}
