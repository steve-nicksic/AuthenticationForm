package com.tts.authenticationform;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
            UserDetails user =
                User.withDefaultPasswordEncoder()
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build();

            return new InMemoryUserDetailsManager(user);
    }
}

/*
    @EnableWebSecurity annotation enables Spring Security's web security support and provides the
    Spring MVC integration. It also extends WebSecurityConfigurationAdapter and overrides a couple
    of its methods to set some specifics fo the web security configuration.
    --------
    We are configuring the HttpSecurity, which will allow to give access to/restrict certain routs
    (defines which URL paths should be secure and which should not).
    We permitted all users to access the '/' and '/home' routes.
    Used formLogin to determine what page will be used for logging users.
    Successful log in redirects to previously requested page that required authentication.
    --
    .and() is used to string all of our configurations together.
    --
    We used permitAll() to make sure all users can access this page as well since everyone needs
    to access this page to log in.
    We configure logout, which also allows all users to access this point.
    --------
    UserService is configured to build a default user for us to log in with.
    Error that withDefaultPasswordEncoder() is deprecated or unsecure. This is okay for testing purposes.
    In a real application, you would want to store the user's password in a more encrypted format.
    --------
    userDetailService() method sets up an in-memory user store with a single user.
    That user is given a user name of "user", a password fo "password", and a role of USER.
 */