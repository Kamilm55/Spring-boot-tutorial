package com.example.SpringBootTutorial.basicauth.security.config;

import com.example.SpringBootTutorial.basicauth.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Slf4j
public class BasicAuthConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //todo: Check why config cannot know roles , it can be hasAnyRole don't return true when role is inside set
    // Learn: -> ROLE_USER("USER") role must be in this form


    //todo: Where is DaoAuthenticationProvider and AuthenticationManager ?
    //Learn:
    // When you use the UserDetailsService, Spring Security is able to use it
    // to load user details, and it internally creates an AuthenticationManager for you.
    // The DaoAuthenticationProvider is often used behind the scenes
    // when you configure authentication using UserDetailsService.
    // AuthenticationManager: Spring Security needs an AuthenticationManager to perform authentication. If you don't explicitly configure one, it will create a default one for you. This default AuthenticationManager typically uses a ProviderManager under the hood.
    // DaoAuthenticationProvider: When you configure authentication using UserDetailsService, a DaoAuthenticationProvider is automatically added to the AuthenticationManager. This provider uses your UserDetailsService to load user details and performs the actual authentication.
    // by default, if you don't specify a custom AuthenticationManager and you configure authentication using a UserDetailsService, Spring Security will use a DaoAuthenticationProvider behind the scenes. The DaoAuthenticationProvider is a common choice for authentication when user details are stored in a data source, such as a database.
    // The DaoAuthenticationProvider is designed to work with a UserDetailsService to load user details and perform authentication based on those details. It internally uses an instance of PasswordEncoder (typically BCryptPasswordEncoder or another suitable implementation) to handle password encoding and matching.


    //to DEBUG:
//    @Autowired
//    private CustomAccessDecisionManager customAccessDecisionManager;


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