/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dht.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dht.controllers",
    "com.dht.repository",
    "com.dht.service",
    "com.dht.components"
})
@Order(2)
public class SpringSecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dxxwcby8l",
                        "api_key", "448651448423589",
                        "api_secret", "ftGud0r1TTqp0CGp5tjwNmkAm-A",
                        "secure", true));
        return cloudinary;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin().usernameParameter("username").passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/").failureUrl("/login?error");
        
        http.logout().logoutSuccessUrl("/login");
        
        http.exceptionHandling().accessDeniedPage("/login?accessDenied");
        
        http.authorizeRequests().antMatchers("/api/**").permitAll()
                .antMatchers("/**").hasRole("ADMIN");
        
        http.csrf().disable();
    }
}
