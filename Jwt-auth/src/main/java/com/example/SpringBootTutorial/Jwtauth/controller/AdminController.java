package com.example.SpringBootTutorial.Jwtauth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/private/admin")
public class AdminController {

    @PostMapping("/acceptRequestForBecomeTeacher")
    public String acceptBecomeTeacherRequest(){
        return "Success! you are teacher now (As an admin)";
    }
}
