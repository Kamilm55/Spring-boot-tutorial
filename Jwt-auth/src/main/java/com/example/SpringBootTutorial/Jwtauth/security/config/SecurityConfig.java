package com.example.SpringBootTutorial.Jwtauth.security.config;

import com.example.SpringBootTutorial.Jwtauth.filter.AuthorizationFilter;
import com.example.SpringBootTutorial.Jwtauth.model.Role;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    public SecurityFilterChain filterChain(HttpSecurity security,AuthorizationFilter authorizationFilter) throws Exception{
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                  req ->
                        {

                            req
                                .requestMatchers("/public/**").permitAll()
                                    .requestMatchers("/auth/**").permitAll()
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
                .addFilterBefore(authorizationFilter , UsernamePasswordAuthenticationFilter.class)
                //In Spring Security, UsernamePasswordAuthenticationFilter is a pre-defined filter that is part of the authentication processing chain. It is responsible for processing HTTP requests related to username/password-based authentication, typically used for login functionality.
                //The .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class) configuration is adding a custom filter (authorizationFilter) before the default UsernamePasswordAuthenticationFilter in the filter chain.
                //Here's a breakdown of what's happening:
                //UsernamePasswordAuthenticationFilter: This filter is part of the Spring Security framework and is designed to handle login (authentication) requests. It looks for HTTP POST requests to a specific login URL (usually /login) and extracts the username and password from the request. It then attempts to authenticate the user based on these credentials.
                //.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class): This configuration statement is part of the HttpSecurity configuration in Spring Security. It's specifying that the custom filter (authorizationFilter) should be added before the UsernamePasswordAuthenticationFilter in the filter chain. The filter chain is the sequence of filters that incoming requests pass through before reaching the actual endpoint.
                //By adding the custom filter before the UsernamePasswordAuthenticationFilter, you are ensuring that your custom logic in the authorizationFilter is executed before the default authentication logic provided by UsernamePasswordAuthenticationFilter. This allows you to perform additional checks or modifications before the authentication process.
                //In summary, the configuration you provided is adding a custom filter for authorization (likely responsible for checking and handling access permissions) before the default Spring Security filter responsible for handling username/password-based authentication. This is a common approach to extend or customize the default behavior of Spring Security in a web application.

////Learn:
//        UsernamePasswordAuthenticationFilter in Spring Security is responsible for automatically checking whether the provided username and password match the credentials stored in the system. When a user submits their credentials (username and password) through a login form, the UsernamePasswordAuthenticationFilter is triggered to handle the authentication process.
//        Here's a simplified overview of how it works:
//        User submits login credentials:
//        The user submits their username and password through a login form, usually via a POST request to a designated login endpoint (e.g., /login).
//        UsernamePasswordAuthenticationFilter intercepts the request: The UsernamePasswordAuthenticationFilter is configured to intercept requests to the login endpoint. It extracts the username and password from the request.
//        AuthenticationManager is invoked: The UsernamePasswordAuthenticationFilter delegates the authentication process to the AuthenticationManager. The AuthenticationManager is responsible for validating the credentials.
//        AuthenticationProvider(s) authenticate the user: The AuthenticationManager uses one or more AuthenticationProvider instances to authenticate the user. Commonly, a DaoAuthenticationProvider is used, which checks the credentials against a user details service (often an implementation of UserDetailsService).
//        Successful or failed authentication: If the credentials are valid, the user is considered authenticated, and the security context is updated to reflect the authenticated user. If the credentials are invalid, the authentication fails, and appropriate actions can be taken (e.g., redirecting the user to a login error page).
//        By default, Spring Security provides a sensible and secure implementation for handling username/password authentication. If you have a custom UserDetailsService and user details stored securely (e.g., in a database), Spring Security will use this information to perform the authentication.

                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults()); // it generates custom login , logout page

        return security.build();
    }


}
