//package com.study.contentmanage.security;
//
//import com.study.base.mybatisplus.entity.Result;
//import com.study.contentmanage.util.BaseUtils;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class AjaxLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
//
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//                                Authentication authentication) throws IOException, ServletException {
//        if(BaseUtils.isAjaxRequest(request)){
//            response.setContentType("application/json;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.write(Result.ok("登出成功").toString());
//            out.flush();
//            out.close();
//        }else {
//            super.onLogoutSuccess(request, response, authentication);
//        }
//    }
//}