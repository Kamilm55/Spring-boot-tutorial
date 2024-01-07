package com.example.SpringBootTutorial.Jwtauth.dto;

import com.example.SpringBootTutorial.Jwtauth.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(
        String name,
        String userName,
        String password,
        Set<Role> authorities
) {
}
