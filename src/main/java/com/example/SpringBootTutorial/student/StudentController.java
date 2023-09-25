//package com.example.SpringBootTutorial.student;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/v1/students") // after localhost:8080/ ....
//public class StudentController {
//    private final StudentService studentService;
//
//    @Autowired // it should auto-inject
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @GetMapping()//  path for this => @RequestMapping path + @GetMapping(path = ?)
//    public List<Student> getStudents(){
//        return studentService.getStudents();
//    }
//
//    @PostMapping("/signUp") //@RequestBody => Spring will automatically convert the body of the incoming HTTP request into an instance of the specified Java class
//    public void registerStudent(@RequestBody Student student) {
//        studentService.registerStudent(student);
//    }
//
//    @DeleteMapping(path = "/{studentId}/delete")
//    public void deleteStudent(@PathVariable("studentId") Long id){
//        studentService.deleteStudentById(id);
//    }
//
//    @PutMapping("/{studentId}/update")
//    public Student updateStudent(@PathVariable("studentId") Long id , @RequestParam(required = false) String new_email ,@RequestParam(required = false) String new_name){
//        return studentService.updateStudent(id,new_email,new_name);
//    }
//
////    @PostMapping("/addUser")
////    public User addUser (@RequestBody User user){
////        return studentService.
////    }
//
//}
