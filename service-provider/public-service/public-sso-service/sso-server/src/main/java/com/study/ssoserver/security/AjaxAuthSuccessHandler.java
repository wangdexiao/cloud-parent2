package com.study.ssoserver.security;

import com.study.base.mybatisplus.entity.Result;
import com.study.ssoserver.util.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//定义登陆成功返回信息
@Slf4j
public class AjaxAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("商户[" + SecurityContextHolder.getContext().getAuthentication().getPrincipal() +"]登陆成功！");
        //登陆成功后移除session中验证码信息
//            request.getSession().removeAttribute("codeValue");
//            request.getSession().removeAttribute("codeTime");

        if(BaseUtils.isAjaxRequest(request)){
            response.setContentType("application/json;charset=utf-8");
            response.addCookie(new Cookie("JSESSIONID",request.getSession().getId()));
            PrintWriter out = response.getWriter();
            out.write(Result.<User>authSuccess(user).toString());
            out.flush();
            out.close();
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }
}
