package com.study.ssoserver.security;

import com.study.base.mybatisplus.entity.Result;
import com.study.ssoserver.util.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//定义登陆失败返回信息
public class AjaxAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //登陆失败后移除session中验证码信息
//        request.getSession().removeAttribute("codeValue");
//        request.getSession().removeAttribute("codeTime");

        if(BaseUtils.isAjaxRequest(request)){
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(
                    Result.authFail("请检查用户名、密码或验证码是否正确")
                        .toString());
            out.flush();
            out.close();
        }else {
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
