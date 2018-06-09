package com.netease.exam.myshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //允许显示iframe
        http.headers().frameOptions().disable();
        //关闭csrf防护
        http.csrf().disable();
        //配置自定义登录表单
        http
            .authorizeRequests()
            .antMatchers("/css/**",
                    "/html/**",
                    "/image/**",
                    "/js/**",
                    "/",
                    "/show",
                    "/files/**",
                    "/login",
                    "/h2-console/**").permitAll()
            .antMatchers("/public",
                    "/publicSubmit",
                    "/edit",
                    "/editSubmit",
                    "/api/upload",
                    "/api/delete").hasRole("SELLER")
            .antMatchers("/account",
                        "/settleAccount",
                        "/api/buy").hasRole("BUYER")
            .antMatchers("/**").hasAnyRole("BUYER","SELLER")
            .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
    }
}
