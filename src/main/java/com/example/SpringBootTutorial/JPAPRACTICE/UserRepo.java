package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User u set u.projects = :projects where u.id = :uid")
    void updateUserByProjects(@Param("uid") Long uid,@Param("projects") Set<Project> projects);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.userEmail = :email WHERE u.id = :id")
    void updateUserEmailById(@Param("id")Long id,@Param("email")String email);

}
