package com.example.SpringBootTutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Component
@Service // same as component but more Semantic
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void registerStudent(Student student){
    Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
    if(optionalStudent.isPresent()){
        throw new IllegalStateException("This email has been taken already!");
    }

     studentRepository.save(student);
    }
}
