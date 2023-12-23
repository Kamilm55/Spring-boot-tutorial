package com.example.SpringBootTutorial.SpringSecurity.InMemorySecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class InMemorySecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Learn:
    // In Spring Security, UserDetailsService is an interface that is used to retrieve user details from a data store,
    // typically a database. The primary purpose of UserDetailsService is to load user-specific data, such as username,
    // password, and granted authorities, which is required by Spring Security to perform authentication and authorization.
    @Bean
    public/* UserDetailsService*/  UserDetailsManager users() {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder().encode("pass"))
                .roles("USER")
                .build();

        UserDetails teacher = User.builder()
                .username("teacher")
                .password(passwordEncoder().encode("pass"))
                .roles("TEACHER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("pass"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin,teacher);
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
//        security
//                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(x -> x.requestMatchers("/public/**", "/auth/**").permitAll())
//                .authorizeHttpRequests(x -> x.requestMatchers("/private/user/**").hasRole("USER"))
//                .authorizeHttpRequests(x -> x.requestMatchers("/private/admin/**").hasRole("ADMIN"))
//                .authorizeHttpRequests(x -> x.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
//
//        return security.build();
//    }
}
