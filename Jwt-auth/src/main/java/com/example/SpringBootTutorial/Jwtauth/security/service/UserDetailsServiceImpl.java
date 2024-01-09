package com.example.SpringBootTutorial.Jwtauth.security.service;

import com.example.SpringBootTutorial.Jwtauth.service.UserService;
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


        log.info("Loading user details for username: {}", username);
        UserDetails userDetails = userService.getUserByUsername(username);
        log.info("User details loaded successfully for username: {}", username);
        log.info("User roles: {}", userDetails.getAuthorities());

        return userDetails;
    }
}
