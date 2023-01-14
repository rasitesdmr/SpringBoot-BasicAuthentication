package com.example.basicauthentication4.configuration;


import com.example.basicauthentication4.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
    1-) UserDetailsService : Kullanıcının kimlik doğrulama ve yetkilendirme bilgilerini almak için kullanılan
                             Spring Security çerçevesindeki temel bir arabirimdir.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    @Autowired
    CustomUserDetailsService userDetailsService;





    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.cors().and().authorizeHttpRequests()
                .antMatchers("/homepage")
                .hasAnyAuthority("USER", "ADMIN", "PATRON")
                .and()
                .authorizeHttpRequests()
                .antMatchers("/dashboard")
                .hasAnyAuthority("ADMIN", "PATRON")
                .and()
                .authorizeHttpRequests()
                .antMatchers("/authority")
                .hasAnyAuthority("PATRON")
                .and()
               .httpBasic();
            /*    .and()
                .formLogin();*/
//                .loginPage("/login").permitAll()
//                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");

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
