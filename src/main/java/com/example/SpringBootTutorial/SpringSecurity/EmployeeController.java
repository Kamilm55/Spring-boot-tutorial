package com.example.SpringBootTutorial.SpringSecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }
    @GetMapping("/goodBye")
    public ResponseEntity<String> goodBye(){
        return ResponseEntity.ok("Good Bye");
    }
}
