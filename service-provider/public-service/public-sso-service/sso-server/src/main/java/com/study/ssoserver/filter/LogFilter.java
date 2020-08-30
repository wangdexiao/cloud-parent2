//package com.study.ssoserver.filter;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.FilterConfig;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Enumeration;
//
//@Slf4j
//@Component
//public class LogFilter implements Filter {
//
////    private List<String> excludes;
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpReq = (HttpServletRequest) req;
//        String requestURI = httpReq.getRequestURI();
//        log.error("content-manage进来请求:" + requestURI);
//        Enumeration<String> headerNames = httpReq.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            String headerValue = httpReq.getHeader(headerName);
//            log.error("请求头:" + headerName + ":" + headerValue);
//        }
//
//        Cookie[] cookies = httpReq.getCookies();
//        if(cookies != null && cookies.length > 0){
//            for (Cookie cookie: cookies){
//                log.error("cookie: " + cookie.getName() + ":" + cookie.getValue());
//            }
//        }
//        chain.doFilter(req,resp);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//}