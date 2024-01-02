package com.example.SpringBootTutorial.basicauth.security.service;

import com.example.SpringBootTutorial.basicauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        if(username.isEmpty())
//            throw new IllegalStateException("Username should not be empty");

        System.out.println(username);
        log.warn(username);

        log.info("Loading user details for username: {}", username);
        UserDetails userDetails = userService.getUserByUsername(username);
        log.info("User details loaded successfully for username: {}", username);
        log.info("User roles: {}", userDetails.getAuthorities());

        return userDetails;
    }
}
