//package com.study.contentmanage.filter;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.ArrayUtils;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Base64Utils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//import sun.misc.Request;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 使用这个就不用解析token获取信息了
// */
//@Component
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        String authToken = request.getHeader("auth-token");
//        if(!StringUtils.isEmpty(authToken)){
//            //base64 解码
//            String authTokenJson = new String(Base64Utils.decodeFromString(authToken));
//            //转成json对象
//            JSONObject jsonObject = JSON.parseObject(authTokenJson);
//            JSONObject jsonObject = JSON.parseObject(authTokenJson);
//            //用户权限
//            String authorities = ArrayUtils.toString(jsonObject.getJSONArray("authorities").toArray());
//
//            //构建一个Authentication对象,springsecurity 就会用于权限判断
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    jsonObject.get("principal"), null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
//            );
//            //请求详情
//            authenticationToken.setDetails(jsonObject.get("details"));
//
//            //传递给上下文,这样服务就可以获取对应数据
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//
//        // 放行请求
//        chain.doFilter(request, response);
//    }
//}
