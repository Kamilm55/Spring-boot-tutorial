package com.example.SpringBootTutorial.SpringSecurity.InMemorySecurity.InMemorySecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/public")
public class PublicController {

    @PostMapping("/publicMethod")
    public String publicMethod(){
        return "WORKS public method";
    }
}
