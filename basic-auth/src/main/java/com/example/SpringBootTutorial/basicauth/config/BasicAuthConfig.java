package com.example.SpringBootTutorial.basicauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicAuthConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(){
//
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
//        security
//                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(x -> x.requestMatchers("/public/**", "/auth/**").permitAll())
//                .authorizeHttpRequests(x -> x.requestMatchers("/private/teacher/**").hasRole("TEACHER"))
//                .authorizeHttpRequests(x -> x.requestMatchers("/private/admin/**").hasRole("ADMIN"))
//                .authorizeHttpRequests(x -> x.requestMatchers("/private/addCourse").hasAnyRole("ADMIN","TEACHER"))
//                .authorizeHttpRequests(x -> x.requestMatchers("/private/doQuiz").hasAnyRole("USER"))
//                .authorizeHttpRequests(x -> x.anyRequest().authenticated()) // for any user that has USER or ADMIN or TEACHER role
//                .httpBasic(Customizer.withDefaults());
//
//        return security.build();
//    }
}
