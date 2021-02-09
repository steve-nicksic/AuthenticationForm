package com.tts.authenticationform;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void  configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                .logout()
                        .permitAll();
    }
    
}

/*
    We are configuring the HttpSecurity, which will allow to give access to/restrict certain routs.
    We permitted all users to access the '/' and '/home' routes.
    Used formLogin to determine what page will be used for logging users.
    .and() is used to string all of our configurations together.
    We used permitAll() to make sure all users can access this page as well since everyone needs
    to access this page to log in.
    We configure logout, which also allows all users to access this point.

 */