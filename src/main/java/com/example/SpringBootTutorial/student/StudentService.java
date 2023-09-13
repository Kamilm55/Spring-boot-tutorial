package com.example.SpringBootTutorial.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

//@Component
@Service // same as component but more Semantic
public class StudentService {
    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1,"Kamil","k@gsmail.com", LocalDate.of(2023,9,10),19
                )
        );
    }
}
