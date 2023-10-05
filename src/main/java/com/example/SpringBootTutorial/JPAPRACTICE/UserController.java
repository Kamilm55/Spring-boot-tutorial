package com.example.SpringBootTutorial.JPAPRACTICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("updateUserProjects")
    public void updateUserProjects(){
        userService.updateUserProjects();
    }

    @GetMapping("getUser")
    public Optional<User> getUserWithId(@RequestParam Long userId){
      return userService.getUserWithId(userId);
    }
}
