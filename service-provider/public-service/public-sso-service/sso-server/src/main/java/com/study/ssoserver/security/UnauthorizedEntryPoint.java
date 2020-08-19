package com.study.ssoserver.security;

import com.study.base.mybatisplus.entity.Result;
import com.study.ssoserver.util.BaseUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未认证处理
 */
@Component
public class UnauthorizedEntryPoint  implements AuthenticationEntryPoint{

    @Value("${path.login.page}")
    private String loginPagePath;

    @Value("${spring.application.name}")
    private String appName;

//    private RequestCache requestCache = new HttpSessionRequestCache2();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        if(BaseUtils.acceptJson(request)){
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/javascript;charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.print(Result.fail(HttpServletResponse.SC_UNAUTHORIZED,"未认证,请登录",
//                    "http://192.168.1.100:4000/login"));
//            writer.flush();
//            writer.close();
//        }else{
            response.sendRedirect(loginPagePath);
//        }

    }

    public static void main(String[] args) {
        System.out.println(Result.fail(HttpServletResponse.SC_UNAUTHORIZED, "未认证,请登录", "test").toString());
    }


}
