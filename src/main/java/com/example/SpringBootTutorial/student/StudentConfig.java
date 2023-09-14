package com.example.SpringBootTutorial.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {


    @Bean // object of Class that manage by Spring ioc container
    CommandLineRunner commandLineRunner (StudentRepository studentRepository) {
        return args -> {
             studentRepository.saveAll(List.of(
                     new Student(
                             1,"Kamil","k@gsmail.com", LocalDate.of(2004,9,10)
                     ),
                     new Student(
                             2,"Samir","k@gsmail.com", LocalDate.of(2000,9,10)
                     ))
            );
        };
    }

}
