package com.example.SpringBootTutorial.Jwtauth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/private/teacher")
public class TeacherController {

    @PostMapping("/giveTask")
    public String giveTask(){
        return " you give task (As a teacher)";
    }

    @PostMapping("/gradeTask")
    public String gradeTask(){
        return "you graded user's task (As a teacher)";
    }

}
