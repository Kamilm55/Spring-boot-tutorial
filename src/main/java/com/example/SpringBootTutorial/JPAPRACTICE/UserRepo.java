package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query("update User u set u.projects = :projects where u.id = :id")
    void updateUserByProjects(Long id, Set<Project> projects);

    @Modifying
    @Transactional
    @Query("update User u set u.userEmail = :name where u.id = :id")
    void updateUserByUserEmail(Long id,String name);
}
