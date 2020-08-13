package com.study.serviceprovider.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Autowired
//    private MyAuthFilter authFilter;
//
//    @Bean
//    public FilterRegistrationBean<MyAuthFilter> authFilter(){
//        //通过FilterRegistrationBean实例设置优先级可以生效
//        //通过@WebFilter无效
//        FilterRegistrationBean<MyAuthFilter> bean = new FilterRegistrationBean<MyAuthFilter>();
//        bean.setFilter(new MyAuthFilter());//注册自定义过滤器
//        bean.setName("myAuthFilter");//过滤器名称
//        bean.addUrlPatterns("/*");//过滤所有路径
//        bean.addInitParameter("exclusions","/loginpage,/login");
////        bean.setOrder(Ordered.HIGHEST_PRECEDENCE -1);
//        return bean;
//    }
}
