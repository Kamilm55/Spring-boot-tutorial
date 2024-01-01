package com.example.SpringBootTutorial.basicauth.dto;

import com.example.SpringBootTutorial.basicauth.model.Role;
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
