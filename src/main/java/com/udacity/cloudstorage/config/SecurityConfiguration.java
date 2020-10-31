package com.udacity.cloudstorage.config;

import com.udacity.cloudstorage.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;

    // inject AuthenticationService in the constructor
    public SecurityConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    // defines how Spring receives authorisation requests
    // which pages require authorisation
    // and how to handle log-outs
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // anyone can access these pages without authorisation
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        // login handled separately by Spring
        http.formLogin()
                .loginPage("/login")
                .permitAll();

        // default redirect upon successful login
        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }
}
