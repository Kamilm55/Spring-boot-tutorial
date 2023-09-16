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

    @Transactional // if we don't use this annotation it set object's value but not in the database
    public Student updateStudent(Long id, String new_email, String new_name) {
        Student student = studentRepository.findStudentById(id);
        if(student ==  null){
            throw new IllegalStateException("There is no student with " + id + " id.");
        }

        // orElseThrows ? => it works with "Optional"
//        Optional<Student> optionalStudent = studentRepository.findStudentById(id).orElseThrow()


        if(new_name != null && new_name.length() >= 2 && !student.getName().equals(new_name)){
            student.setName(new_name);
        }

        if(new_email != null && new_email.length() > 7 ){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(new_email);
            if(optionalStudent.isPresent()){
                throw new IllegalStateException("This email is taken");
            }
            student.setEmail(new_email);
        }
        return studentRepository.save(student);
    }
}
