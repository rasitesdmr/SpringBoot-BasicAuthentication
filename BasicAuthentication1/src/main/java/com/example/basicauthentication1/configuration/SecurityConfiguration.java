package com.example.basicauthentication1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/dashboard")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/authority")
                .authenticated();

        return httpSecurity.build();
    }

}
