package com.study.ssoserver.security;

import com.study.base.mybatisplus.entity.Result;
import com.study.base.mybatisplus.entity.UserInfoEntity;
import com.study.ssoserver.util.BaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//定义登陆成功返回信息
@Slf4j
public class JsonAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("商户[" + SecurityContextHolder.getContext().getAuthentication().getPrincipal() +"]登陆成功！");
        //登陆成功后移除session中验证码信息
//            request.getSession().removeAttribute("codeValue");
//            request.getSession().removeAttribute("codeTime");

        if(BaseUtils.acceptJson(request)){
            response.setContentType("application/json;charset=utf-8");
//            response.addCookie(new Cookie("JSESSIONID",request.getSession().getId()));
            PrintWriter out = response.getWriter();

            HttpSession session = request.getSession(false);

            if(session != null){
                response.addCookie(new Cookie("JSESSIONIDD",session.getId()));
            }
            response.addCookie(new Cookie("test","1111111"));
            String redirectUrl = "";
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if(savedRequest != null){
                redirectUrl = savedRequest.getRedirectUrl();
                redirectUrl = StringUtils.isEmpty(redirectUrl) ? "no saved request" : redirectUrl;
            }

            out.write(Result.authSuccess(redirectUrl).toString());
            out.flush();
            out.close();
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }
}
