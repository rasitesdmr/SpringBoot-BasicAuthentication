package com.example.basicauthentication2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.formLogin();

        httpSecurity.authorizeHttpRequests()
                .antMatchers("/api/homepage")
                .hasAnyRole("USER","ADMIN","PATRON")
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/dashboard")
                .hasAnyRole("ADMIN","PATRON")
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/authority")
                .hasAnyRole("PATRON");

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("Raşit").password("{noop}3434").roles("ADMIN")
                .and()
                .withUser("Ömer").password("{noop}2323").roles("USER")
                .and()
                .withUser("Mustafa").password("{noop}3535").roles("PATRON");

        return authenticationManagerBuilder.build();
    }

}
