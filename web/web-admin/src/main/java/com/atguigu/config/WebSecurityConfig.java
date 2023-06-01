package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @projectName: shf-parent
 * @package: com.atguigu.config
 * @className: WebSecurityConfig
 * @author: kong
 * @description: TODO
 * @date: 2023/5/30 19:56
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity// 开启springsecurity 系统会提供一个默认的登录页面
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        //配置可以匿名访问的资源及其他所有资源需要认证之后才能访问
        http.authorizeRequests().antMatchers("/static/**","/login").permitAll().anyRequest().authenticated();
        //配置登录的地址及登录成功之后去往的地址
        http.formLogin().loginPage("/login").defaultSuccessUrl("/");
        //配置登出的路径及登出成功去往的地址
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        //关闭跨域请求伪造功能
        http.csrf().disable();
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeineHandler());
    }
}
