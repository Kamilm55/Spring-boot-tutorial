package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    @Modifying
    @Transactional
    @Query("update Project p set p.user = :user where p.id = :projectId")
    void updateProjectByUserId(User user, Long projectId);
}
