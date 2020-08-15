//package com.study.apigetway.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.ZuulFilterResult;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Base64Utils;
//
//import javax.servlet.*;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//
//@Slf4j
//@Component
//public class AuthenticationFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        //获取从security上下文中获取认证信息
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        //jwt令牌有效,则会解析用户信息封装到Oauth2Authentication对象中
//        if(!(authentication instanceof OAuth2Authentication)){
//            chain.doFilter(request,response);
//            return;
//        }
//
//
//        //用户信息
//        Object principal = authentication.getPrincipal();
//        //此用户拥有的权限
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
//        //请求
//        Object details = authentication.getDetails();
//
//
//        //封装传输的数据
//        Map<String, Object> result = new HashMap<>();
//        result.put("principal", principal);
//        result.put("details", details);
//        result.put("authorities", authoritySet);
//
//        //获取当前请求的上线文
//        try {
//            RequestContext context = RequestContext.getCurrentContext();
//            String base64 = Base64Utils.encodeToString(JSON.toJSONString(result).getBytes("UTF-8"));
//            context.addZuulRequestHeader("auth-token",base64);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        chain.doFilter(request,response);
//    }
//
//
//
//}
