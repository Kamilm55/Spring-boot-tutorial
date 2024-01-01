package com.example.SpringBootTutorial.basicauth.security.config;

import com.example.SpringBootTutorial.basicauth.model.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security
                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x
//                                .requestMatchers("/public/**").permitAll()
//                                .requestMatchers("/private/addCourse").hasAnyRole(
//                                        Role.TEACHER.name(),
//                                        Role.ADMIN.name())
//                                .requestMatchers("/private/user/**").hasRole(Role.USER.name())
//                                .requestMatchers("/private/teacher/**").hasRole(Role.TEACHER.name())
//                                .requestMatchers("/private/admin/**").hasRole(Role.ADMIN.name())
                                .requestMatchers("/private/**").hasAnyRole(
                                        Role.USER.name(),
                                        Role.TEACHER.name(),
                                        Role.ADMIN.name()
                                )
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return security.build();
    }

    // For h2 database (not working properly)
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security, HandlerMappingIntrospector introspector) throws Exception{
//
//        MvcRequestMatcher.Builder mvcRequestBuilder = new MvcRequestMatcher.Builder(introspector);
//
//
//        security
//                .headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .csrf(csrfConfig ->
//                        csrfConfig.ignoringRequestMatchers(mvcRequestBuilder.pattern("/public/**"),
//                                        mvcRequestBuilder.pattern("/private/**"))
//                                .ignoringRequestMatchers(PathRequest.toH2Console()))
//                .authorizeHttpRequests(x ->
//                        x
//                                .requestMatchers(mvcRequestBuilder.pattern("/public/**")).permitAll()
//                                .requestMatchers(mvcRequestBuilder.pattern("/private/addCourse")).hasAnyRole(
//                                        Role.TEACHER.name(),
//                                        Role.ADMIN.name())
//                                .requestMatchers(mvcRequestBuilder.pattern("/private/user/**")).hasRole(Role.USER.name())
//                                .requestMatchers(mvcRequestBuilder.pattern("/private/teacher/**")).hasRole(Role.TEACHER.name())
//                                .requestMatchers(mvcRequestBuilder.pattern("/private/admin/**")).hasRole(Role.ADMIN.name())
//                                .requestMatchers(mvcRequestBuilder.pattern("/private/**")).hasAnyRole(
//                                        Role.USER.name(),
//                                        Role.TEACHER.name(),
//                                        Role.ADMIN.name()
//                                )
//                                .requestMatchers(PathRequest.toH2Console()).permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//
//        return security.build();
//
//    }
}

/*     x
                        .requestMatchers(mvcRequestBuilder.pattern("/public/**")).permitAll()
                        .requestMatchers(mvcRequestBuilder.pattern("/private/admin/**")).hasRole(Role.ADMIN.name())
                      .requestMatchers(mvcRequestBuilder.pattern("/private/addCourse")).hasAnyRole(
                                      Role.TEACHER.name(),
                                      Role.ADMIN.name())
                        .requestMatchers(mvcRequestBuilder.pattern("/private/**")).hasAnyRole(
                                      Role.USER.name(),
                                      Role.TEACHER.name(),
                                      Role.ADMIN.name()
                                      )
                        .requestMatchers(mvcRequestBuilder.pattern("/private/teacher/**")).hasRole(Role.TEACHER.name())
                        .requestMatchers(mvcRequestBuilder.pattern("/private/user/**")).hasRole(Role.USER.name())
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()*/