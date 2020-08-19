package com.study.ssoserver.config;

import com.study.ssoserver.security.*;
import com.study.ssoserver.service.MyUserService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@MapperScan("com.sso.authserver.mapper")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService userService;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    private JsonAccessDeniedHandler accessDeniedHandler;






    //将授权服务需要的两个bean，提供给它
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("$2a$10$cuJ4Br43Ih4GJKtXeI7vXuCqee7N6CbQ26.P2wUoXdT2KJKiKZOay").roles("admin")
//                .and()
//                .withUser("user").password("$2a$10$cuJ4Br43Ih4GJKtXeI7vXuCqee7N6CbQ26.P2wUoXdT2KJKiKZOay").roles("user");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/login*","/logout","/test","/clients/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                    .csrf().disable()
                    .formLogin().loginPage("/loginpage")
                                .successHandler(new JsonAuthSuccessHandler())
                                .failureHandler(new JsonAuthFailHandler())
                    .usernameParameter("username")
                    .passwordParameter("passwd")
                    .loginProcessingUrl("/login")
                // 设置没有权限访问的处理程序
                .and().exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler)
                .and()
                    .logout().logoutUrl("/logout")
                        .logoutSuccessHandler(new JsonLogoutSuccessHandler())
                ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/webjars/**")
                .antMatchers("/assets/**")
                .antMatchers("/favicon.ic")
                ;
    }
}
