package com.example.SpringBootTutorial.student;

import jakarta.transaction.Transactional;
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

    public void deleteStudentById(Long id) {
       boolean studentExists =  studentRepository.existsById(id);
       if(!studentExists){
           throw new IllegalStateException("There is no student with " + id + " id.");
       }
       studentRepository.deleteById(id);
    }

//    @Transactional
    public Student updateStudent(Long id, String new_email, String new_name) {
        Student student = studentRepository.findStudentById(id);
//        if(student){
//            throw new IllegalStateException("There is no student with " + id + " id.");
//        }
        // orElseThrows ?


        if(new_email != null && new_email.length() > 10 && !student.getEmail().equals(new_email)){
            student.setEmail(new_email);
        }
        if(new_name != null && new_name.length() >= 2 && !student.getName().equals(new_name)){
            student.setName(new_name);
        }
        return studentRepository.save(student);
    }
}
