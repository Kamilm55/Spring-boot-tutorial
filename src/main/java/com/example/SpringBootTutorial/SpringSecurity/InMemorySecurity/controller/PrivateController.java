package com.example.SpringBootTutorial.SpringSecurity.InMemorySecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/private")
public class PrivateController {

    @PostMapping("/privateUser")
    public String privateUser(){
        return "Only authenticated user public method (USER , TEACHER , ADMIN)";
    }

    @PostMapping("/addCourse")
//    @PreAuthorize(value = "hasAnyRole('TEACHER','ADMIN')")
    public String addCourse(){
        return "Teacher and Admin can add course but USER not";
    }

    @PostMapping("/doQuiz")
    public String doQuiz(){
        return "only USER method ";
    }

}
