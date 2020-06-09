package com.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @description:
 * @author: 小猴子
 * @date: 2019-11-15 9:17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .antMatchers(   "/",
                        "/home/index.*",
                        "/login/**.*",
                        "/login/login",
                        /*"/**.*",*/
                        "/error"
                        ).permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login/login.do")/*.defaultSuccessUrl("/home/userIndex.do")*/
                .loginProcessingUrl("/login/process")
                .successForwardUrl("/login/success.do")
                .failureForwardUrl("/login/failure.do")
                .and()
                .logout().logoutSuccessUrl("/login/login.do")
                .and()
                .headers().frameOptions().disable();
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                            .username("user")
                            .password("possword")
                            .roles("admin")
                            .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .passwordEncoder()

    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/css/**","/favicon.ico");
        super.configure(web);
    }
}
