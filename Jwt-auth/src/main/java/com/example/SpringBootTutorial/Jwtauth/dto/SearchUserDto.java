package com.example.SpringBootTutorial.Jwtauth.dto;

import lombok.Builder;

@Builder
public record SearchUserDto(
        String username,
        String password
) {
}
