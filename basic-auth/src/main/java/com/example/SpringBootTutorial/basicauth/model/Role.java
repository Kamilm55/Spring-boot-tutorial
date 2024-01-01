package com.example.SpringBootTutorial.basicauth.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN"),
    TEACHER("TEACHER");

    private final String value;
    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
