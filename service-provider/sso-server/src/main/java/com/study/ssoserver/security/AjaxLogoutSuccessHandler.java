package com.study.ssoserver.security;

import com.study.base.mybatisplus.entity.Result;
import com.study.ssoserver.util.BaseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            String redirectUri= request.getParameter("redirect_uri");//aa即为前端传来自定义跳转url地址
            response.sendRedirect(redirectUri);//实现自定义重定向
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}