package com.example.SpringBootTutorial.basicauth.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    TEACHER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
