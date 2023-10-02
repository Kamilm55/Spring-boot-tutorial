package com.example.SpringBootTutorial.JPAPRACTICE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectRepoTest {
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    @Autowired
    public ProjectRepoTest(ProjectRepo projectRepo, UserRepo userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @Test
    void updateProjectByUserId() {
        Project p1 = projectRepo.findById(1L).orElseThrow(() -> new RuntimeException("Project with ID 1 not found"));

        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
        projectRepo.updateProjectByUserId(user1, p1.getId());

        assertEquals(p1.getUser() , user1 , "User is not updated");

    }
}