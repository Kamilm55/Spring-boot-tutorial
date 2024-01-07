package com.example.SpringBootTutorial.Jwtauth.security.config;

import com.example.SpringBootTutorial.Jwtauth.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsService
            , PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                  req ->
                        {

                            req
                                .requestMatchers("/public/**").permitAll()

                                .requestMatchers("/private/addCourse").hasAnyRole( // the most specific on the top
                                        Role.ROLE_TEACHER.getValue(),
                                        Role.ROLE_ADMIN.getValue())
                                .requestMatchers("/private/user/**").hasRole(Role.ROLE_USER.getValue())
                                .requestMatchers("/private/teacher/**").hasRole(Role.ROLE_TEACHER.getValue())
                                .requestMatchers("/private/admin/**").hasRole(Role.ROLE_ADMIN.getValue())
                                .requestMatchers("/private/**").hasAnyRole(
                                    Role.ROLE_USER.getValue(),
                                    Role.ROLE_TEACHER.getValue(),
                                    Role.ROLE_ADMIN.getValue()
                                 )
                                 .anyRequest().authenticated();// rest of the non-secured url for roles but open for authorized
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults()); // it generates custom login , logout page

        return security.build();
    }


}
