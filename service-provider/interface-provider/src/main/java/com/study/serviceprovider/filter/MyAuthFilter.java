//package com.study.serviceprovider.filter;
//
//
//import com.study.serviceprovider.controller.LoginController;
//import org.springframework.util.CollectionUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.List;
//
///**
// * 测试session cookie 登录过滤器
// */
////@Configuration
////@Order(value = Ordered.HIGHEST_PRECEDENCE -1)
//public class MyAuthFilter implements Filter {
//
//    private List exclusions;
//    @Override
//    public void init(FilterConfig filterConfig)  {
//        exclusions = CollectionUtils.arrayToList(filterConfig.getInitParameter("exclusions").split(","));
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String requestURI = httpServletRequest.getRequestURI();
//
//        if(exclusions.contains(requestURI)){
//            chain.doFilter(request,response);
//        }else {
//            LoginController.User userBean = (LoginController.User) httpServletRequest.getSession().getAttribute("userBean");
//            if(userBean != null){
//                chain.doFilter(request,response);
//            }else {
//                httpServletRequest.getRequestDispatcher("/loginpage").forward(request,response);
//            }
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
