package com.example.basicauthentication3.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
    1-) UserDetailsService : Kullanıcının kimlik doğrulama ve yetkilendirme bilgilerini almak için kullanılan
                             Spring Security çerçevesindeki temel bir arabirimdir.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.formLogin();

        httpSecurity.authorizeHttpRequests()
                .antMatchers("/api/homepage")
                .hasAnyAuthority("USER", "ADMIN", "PATRON")
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/dashboard")
                .hasAnyAuthority("ADMIN", "PATRON")
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/authority")
                .hasAnyAuthority("PATRON");

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService);

        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
