package com.example.SpringBootTutorial.basicauth.controller;

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
