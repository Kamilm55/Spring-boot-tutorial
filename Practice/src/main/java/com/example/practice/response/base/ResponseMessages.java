package com.example.practice.response.base;

import org.springframework.http.HttpStatus;


public interface ResponseMessages {
    String getKey();
    String getMessage();
    HttpStatus getStatus();
}