package com.example.SpringBootTutorial.Jwtauth.service.base;

public interface TokenReader <T>{
    T read(String token);
}
