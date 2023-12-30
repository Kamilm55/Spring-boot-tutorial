package com.example.SpringBootTutorial.SpringSecurity.InMemorySecurity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @PostMapping("/signIn")
    public String publicMethod(){
        return "signIn public method WORKS";
    }
}
