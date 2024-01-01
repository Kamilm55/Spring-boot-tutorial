package com.example.SpringBootTutorial.basicauth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/private/user")
public class UserController {

    @PostMapping("/makeRequestForBecomeTeacher")
    public String makeRequestForBecomeTeacher(){
        return "you send request to become teacher now (As a user)" ;
    }

}
