package com.example.SpringBootTutorial.basicauth.service;

import com.example.SpringBootTutorial.basicauth.dto.CreateUserRequest;
import com.example.SpringBootTutorial.basicauth.model.User;


public interface UserService {
    User getUserByUsername(String username);
    void createUser(CreateUserRequest userRequest);
    User getUser(Long id);
}
