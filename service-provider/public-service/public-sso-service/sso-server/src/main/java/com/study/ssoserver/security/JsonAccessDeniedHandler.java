package com.study.ssoserver.security;

import com.study.base.mybatisplus.entity.Result;
import com.study.ssoserver.util.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 没有权限访问
 *
 * @author lixingwu
 */
@Slf4j
@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
//        if(BaseUtils.acceptJson(request)){
            log.error("没有权限访问，{}", accessDeniedException);

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/javascript;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(Result.accessFail(accessDeniedException.getMessage()));
            writer.flush();
            writer.close();
//        }else {
//            super.
//        }

    }
}
