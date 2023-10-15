package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserRepoTest {

    private final ProjectRepo projectRepo;

    private final UserRepo userRepo;
    @Autowired
    public UserRepoTest(ProjectRepo projectRepo, UserRepo userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }
    @Test
    void updateUserByProjects() {
        // Ensure that there is a user with ID 1 in the database
        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        // Fetch two distinct projects
        Project p1 = projectRepo.findById(3L).orElseThrow(() -> new RuntimeException("Project with ID 1 not found"));
        Project p2 = projectRepo.findById(4L).orElseThrow(() -> new RuntimeException("Project with ID 2 not found"));

        // Create a set of projects
        Set<Project> user1Projects = new HashSet<>();
        user1Projects.add(p1);
        user1Projects.add(p2);


        // Update the user's projects with query
//        userRepo.updateUserByProjects(user1.getId(), user1Projects);

        user1.setProjects(user1Projects);
        System.out.println(user1);
        userRepo.save(user1);

        // Optionally, assert the expected behavior
        User updatedUser = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        assertEquals(user1Projects, projectRepo.getProjectsByUserId(updatedUser.getId()), "User's projects should have been updated");
    }


    @Test
//    @Transactional // in test this not working , it works in service (business logic) , controller (complex business logic) or REPOSITORIES(every db operations are commited immediately)
    void updateUserByUserEmail() {
<<<<<<< HEAD
        User user1 = userRepo.findById(2L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
=======
        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
>>>>>>> parent of c2f53b2 (CRUD)

        String newEmail = "updated2@gmail";

        userRepo.updateUserEmailById(user1.getId(), newEmail); // update with query

//        user1.setUserEmail(newEmail); // this updates
//        userRepo.save(user1);         // but not in the table
<<<<<<< HEAD
//        User updatedUser = userRepo.findById(2L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
=======
        User updatedUser = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
>>>>>>> parent of c2f53b2 (CRUD)
//        System.out.println(newEmail + " " + updatedUser.getUserEmail());
//        assertEquals(newEmail, updatedUser.getUserEmail(), "User's email should have been updated");
    }


    @Test
    void deleteUser(){
<<<<<<< HEAD
        User user1 = userRepo.findById(3L).orElseThrow(() -> new RuntimeException("User not found"));
=======
        User user1 = userRepo.findById(4L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
>>>>>>> parent of c2f53b2 (CRUD)

        userRepo.deleteById(user1.getId());

    }
   /* @Test
    void insertUser(){
        User user1 = new User();
        user1.setUserName("NEWuser");
        user1.setUserEmail("NEWUSER@gmail.com");
        userRepo.save(user1);

        User updatedUser = userRepo.findById(39L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
        assertEquals(user1, updatedUser, "User's email should have been updated");

    }*/



}