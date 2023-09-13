package com.example.SpringBootTutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students") // after localhost:8080/ ....
public class StudentController {
    private final StudentService studentService;

    @Autowired // it should auto-inject
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()//  path for this => @RequestMapping path + @GetMapping(path = ?)
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
}
