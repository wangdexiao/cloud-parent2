package com.study.contentmanage.security;//package com.mengxuegu.oauth2.sso.annoation;

import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

//@Component
public class MyOAuth2ClientContextFilter  extends OAuth2ClientContextFilter {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        /**
         * Redirect the user according to the specified exception.
         *
         * @param e
         *            The user redirect exception.
         * @param request
         *            The request.
         * @param response
         *            The response.
         */
        @Override
        protected void redirectUser(UserRedirectRequiredException e,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException {

            String redirectUri = e.getRedirectUri();
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(redirectUri);
            Map<String, String> requestParams = e.getRequestParams();
            for (Map.Entry<String, String> param : requestParams.entrySet()) {
                builder.queryParam(param.getKey(), param.getValue());
            }

            if (e.getStateKey() != null) {
                builder.queryParam("state", e.getStateKey());
            }
            HttpSession session = request.getSession(false);
            if(session != null){
                session.setAttribute("redirectUrl",request.getParameter("redirectUrl"));
            }

            this.redirectStrategy.sendRedirect(request, response, builder.build()
                    .encode().toUriString());

//            HttpSession session = request.getSession();
//            session.setAttribute("redirectUrl",request.getParameter("redirectUrl"));
//            Cookie cookie = new Cookie("redirect_url", session.getId());
//            cookie.setPath("/login");
//            response.addCookie(cookie);
//            //todo custom
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/javascript;charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.print(Result.unAuth2code(builder.build().encode().toUriString()).toString());
//            writer.flush();
//            writer.close();
        }


}

