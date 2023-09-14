package com.example.SpringBootTutorial.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    // It returns not null Student optional object , it can be empty or non-empty
    // s is alias for Student entity
    // So, this query is essentially saying, "Select all Student entities (s) where the email property of the
    // Student entity is equal to the value provided as the first parameter (?1) when executing the query."
    // The s is a shorthand alias for the Student entity to make the query more concise and readable.
}
