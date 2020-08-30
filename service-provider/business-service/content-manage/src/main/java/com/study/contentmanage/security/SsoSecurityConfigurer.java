package com.study.contentmanage.security;

import com.study.base.mybatisplus.entity.Result;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

@Component
public class SsoSecurityConfigurer {

        private ApplicationContext applicationContext;

        public SsoSecurityConfigurer(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public void configure(HttpSecurity http) throws Exception {
            OAuth2SsoProperties sso = (OAuth2SsoProperties)this.applicationContext.getBean(OAuth2SsoProperties.class);
            http.apply(new SsoSecurityConfigurer.OAuth2ClientAuthenticationConfigurer(this.oauth2SsoFilter(sso)));
            this.addAuthenticationEntryPoint(http, sso);
        }

        private void addAuthenticationEntryPoint(HttpSecurity http, OAuth2SsoProperties sso) throws Exception {
            ExceptionHandlingConfigurer<HttpSecurity> exceptions = http.exceptionHandling();
            ContentNegotiationStrategy contentNegotiationStrategy = (ContentNegotiationStrategy)http.getSharedObject(ContentNegotiationStrategy.class);
            if (contentNegotiationStrategy == null) {
                contentNegotiationStrategy = new HeaderContentNegotiationStrategy();
            }

            MediaTypeRequestMatcher preferredMatcher = new MediaTypeRequestMatcher((ContentNegotiationStrategy)contentNegotiationStrategy, new MediaType[]{MediaType.APPLICATION_XHTML_XML, new MediaType("image", "*"), MediaType.TEXT_HTML, MediaType.TEXT_PLAIN});
            preferredMatcher.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
            exceptions.defaultAuthenticationEntryPointFor(new MyLoginUrlAuthenticationEntryPoint(sso.getLoginPath()), preferredMatcher);
            exceptions.defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new RequestHeaderRequestMatcher("X-Requested-With", "XMLHttpRequest"));
        }


        private OAuth2ClientAuthenticationProcessingFilter oauth2SsoFilter(OAuth2SsoProperties sso) {
            OAuth2RestOperations restTemplate = ((UserInfoRestTemplateFactory)this.applicationContext.getBean(UserInfoRestTemplateFactory.class)).getUserInfoRestTemplate();
            ResourceServerTokenServices tokenServices = (ResourceServerTokenServices)this.applicationContext.getBean(ResourceServerTokenServices.class);
            OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(sso.getLoginPath());
            filter.setRestTemplate(restTemplate);
            filter.setTokenServices(tokenServices);
            filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                    HttpSession session = request.getSession(false);
                    String redirctUrl = "";
                    if(session != null){
                        redirctUrl = (String) session.getAttribute("redirectUrl");
                    }
                    if(request.getHeader("Accept").contains("application/json")){
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(Result.authSuccess(redirctUrl).toString());
                        out.flush();
                        out.close();
                    }else {
                        response.sendRedirect(redirctUrl);
                    }


                }
            });
            filter.setApplicationEventPublisher(this.applicationContext);
            return filter;
        }

        private static class OAuth2ClientAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
            private OAuth2ClientAuthenticationProcessingFilter filter;

            OAuth2ClientAuthenticationConfigurer(OAuth2ClientAuthenticationProcessingFilter filter) {
                this.filter = filter;
            }

            public void configure(HttpSecurity builder) throws Exception {
                OAuth2ClientAuthenticationProcessingFilter ssoFilter = this.filter;
                ssoFilter.setSessionAuthenticationStrategy((SessionAuthenticationStrategy)builder.getSharedObject(SessionAuthenticationStrategy.class));
                builder.addFilterAfter(ssoFilter, AbstractPreAuthenticatedProcessingFilter.class);
            }
        }
    }
