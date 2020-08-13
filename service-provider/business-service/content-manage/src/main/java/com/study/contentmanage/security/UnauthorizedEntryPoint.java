//package com.study.contentmanage.security;
//
//import com.study.base.mybatisplus.entity.Result;
//import com.study.contentmanage.util.BaseUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * 未认证处理
// */
//@Component
//public class UnauthorizedEntryPoint  implements AuthenticationEntryPoint{
//
//    @Value("${path.login.page}")
//    private String loginPagePath = "";
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        if(BaseUtils.isAjaxRequest(request)){
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/javascript;charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.print(Result.fail(HttpServletResponse.SC_UNAUTHORIZED,"未认证,请登录",
//                    request.getScheme() +"://" + request.getServerName()+ ":" +request.getServerPort() + loginPagePath).toString());
//            writer.flush();
//            writer.close();
//        }else{
//            response.sendRedirect(loginPagePath);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Result.fail(HttpServletResponse.SC_UNAUTHORIZED, "未认证,请登录", "test").toString());
//    }
//
//
//}
