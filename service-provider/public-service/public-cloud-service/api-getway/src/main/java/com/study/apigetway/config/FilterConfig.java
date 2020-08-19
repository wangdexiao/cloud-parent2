package com.study.apigetway.config;

import com.netflix.discovery.converters.Auto;
//import com.study.apigetway.filter.AuthenticationFilter;
import com.study.apigetway.filter.CORSFilter;
import com.study.apigetway.filter.WebHookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Configuration
public class FilterConfig {

    @Autowired
    private CORSFilter corsFilter;

    @Autowired
    private WebHookFilter webHookFilter;

//     @Autowired
//    private AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean<CORSFilter> corsFilterFilterRegistrationBean(){
        FilterRegistrationBean<CORSFilter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterFilterRegistrationBean.setFilter(corsFilter);
        filterFilterRegistrationBean.setName("CORSFilter");
        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.addInitParameter("url-exclude","/ueditor/config");
        return filterFilterRegistrationBean;
    }



    @Bean
    public FilterRegistrationBean<WebHookFilter> webHookFilterFilterRegistrationBean(){
        FilterRegistrationBean<WebHookFilter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE - 1);
        filterFilterRegistrationBean.setFilter(webHookFilter);
        filterFilterRegistrationBean.addUrlPatterns("/actuator/bus-refresh");
        filterFilterRegistrationBean.setName("WebHookFilter");
        return filterFilterRegistrationBean;
    }



//    @Bean
//    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean(){
//        FilterRegistrationBean<AuthenticationFilter> filterFilterRegistrationBean
//                = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        filterFilterRegistrationBean.setFilter(authenticationFilter);
//        filterFilterRegistrationBean.addUrlPatterns("/*");
//        filterFilterRegistrationBean.setName("AuthenticationFilter");
//        return filterFilterRegistrationBean;
//    }
}
