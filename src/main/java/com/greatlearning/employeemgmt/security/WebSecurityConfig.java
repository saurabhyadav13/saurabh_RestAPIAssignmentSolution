package com.greatlearning.employeemgmt.security;

import com.greatlearning.employeemgmt.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// This class will be annotated with Configuration and all the beans will be defined here
// We will make use of WebSecurityConfigurerAdapter to ensure that the right people have access to the right pages
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .authorizeRequests()
                .antMatchers(HttpMethod.DELETE,"/employees/*").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/employees").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/employees","/employees/*").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/h2-console/").permitAll()
                .antMatchers("/adduser","/addrole").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and()
                .cors().disable().csrf().disable().headers().frameOptions().disable();
    }

}
