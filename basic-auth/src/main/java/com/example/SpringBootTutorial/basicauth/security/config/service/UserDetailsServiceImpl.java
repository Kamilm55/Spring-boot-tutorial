package com.example.SpringBootTutorial.basicauth.security.config.service;

import com.example.SpringBootTutorial.basicauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.isEmpty())
            throw new IllegalStateException("Username should not be empty");


        return userService.getUserByUsername(username);
    }
}
