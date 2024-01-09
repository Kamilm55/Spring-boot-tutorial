package com.example.SpringBootTutorial.Jwtauth.service.base;

public interface TokenGenerator <T> {
    String generate(T obj);
}
