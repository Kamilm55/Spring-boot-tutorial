package com.example.SpringBootTutorial.Jwtauth.service;

import com.example.SpringBootTutorial.Jwtauth.dto.CreateUserRequest;
import com.example.SpringBootTutorial.Jwtauth.model.User;


public interface UserService {
    User getUserByUsername(String username);
    void createUser(CreateUserRequest userRequest);
    User getUserById(Long id);
}
