package com.study.ssoserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/loginpage").setViewName("loginpage");
//        registry.addViewController("/error").setViewName("error");
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/typography.html").setViewName("typography");
        registry.addViewController("/elements.html").setViewName("elements");
        registry.addViewController("/buttons.html").setViewName("buttons");
        registry.addViewController("/treeview.html").setViewName("treeview");
        registry.addViewController("/jquery-ui.html").setViewName("jquery-ui");
        registry.addViewController("/nestable-list.html").setViewName("nestable-list");
        registry.addViewController("/tables.html").setViewName("tables");
        registry.addViewController("/jqgrid.html").setViewName("jqgrid");
        registry.addViewController("/form-elements.html").setViewName("form-elements");
        registry.addViewController("/form-wizard.html").setViewName("form-wizard");
        registry.addViewController("/wysiwyg.html").setViewName("wysiwyg");
        registry.addViewController("/dropzone.html").setViewName("dropzone");
        registry.addViewController("/widgets.html").setViewName("widgets");
        registry.addViewController("/calendar.html").setViewName("calendar");
        registry.addViewController("/gallery.html").setViewName("gallery");
        registry.addViewController("/profile.html").setViewName("profile");
        registry.addViewController("/inbox.html").setViewName("inbox");
        registry.addViewController("/pricing.html").setViewName("pricing");
        registry.addViewController("/invoice.html").setViewName("invoice");
        registry.addViewController("/timeline.html").setViewName("timeline");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/faq.html").setViewName("faq");
        registry.addViewController("/error-404.html").setViewName("error-404");
        registry.addViewController("/error-500.html").setViewName("error-500");
        registry.addViewController("/grid.html").setViewName("grid");
        registry.addViewController("/blank.html").setViewName("blank");

//        registry.addViewController("/clientmanage.html").setViewName("clientmanage");
    }
}
