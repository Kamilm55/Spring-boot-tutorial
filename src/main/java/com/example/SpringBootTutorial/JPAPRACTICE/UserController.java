package com.example.SpringBootTutorial.JPAPRACTICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {
    private  final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("updateEmail")
    public void updateUserEmailById(@RequestParam String newEmail){
         userService.updateUserEmailById(newEmail);
    }
}
