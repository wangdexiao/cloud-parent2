package com.study.contentmanage.security;

import com.study.base.mybatisplus.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@Slf4j
//@Component
public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {


    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String redirectUrl = null;
        // redirect to login page. Use https if forceHttps true

        redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);

//        log.error("登录页面：" + redirectUrl);

        if(request.getHeader("Accept").contains("application/json")){
            response.setContentType("application/json");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(Result.unAuth2code(redirectUrl).toString());
            out.flush();
            out.close();
        }else {
            redirectStrategy.sendRedirect(request, response, redirectUrl);
        }



    }
}
