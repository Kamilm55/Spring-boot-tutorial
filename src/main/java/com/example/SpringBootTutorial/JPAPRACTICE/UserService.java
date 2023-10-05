package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final ProjectRepo projectRepo; // we should use project repo via project service (best practice) , but it is for just testing
    private final UserRepo userRepo;
    @Autowired
    public UserService(ProjectRepo projectRepo, UserRepo userRepo) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
    }

    @Transactional // this is atomic (a unit of work) // if  error occurs rollback all operations in this method
    public void updateUserEmailById(String newEmail) {
        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        userRepo.updateUserEmailById(user1.getId(), newEmail);


        String newName = "kamil1234";
        userRepo.updateUserNameById(user1.getId(), newName);

        // all are executed , if exception happens all should rollback
//        throw new  RuntimeException("Error occured");
    }

    @Transactional
    public void updateUserProjects() {
        // Ensure that there is a user with ID 1 in the database
        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        // Fetch two distinct projects
        Project p1 = projectRepo.findById(1L).orElseThrow(() -> new RuntimeException("Project with ID 1 not found"));
        Project p2 = projectRepo.findById(2L).orElseThrow(() -> new RuntimeException("Project with ID 2 not found"));

        // Create a set of projects
        Set<Project> user1Projects = new HashSet<>();
        user1Projects.add(p1);
        user1Projects.add(p2);


        // Update the user's projects with query
//        userRepo.updateUserByProjects(user1.getId(), user1Projects);



        System.out.println(user1);

        p1.setUser(user1); // first we must set user to projects
        p2.setUser(user1);

//        user1.setProjects(user1Projects); // then projects to user

        userRepo.save(user1);
        System.out.println(user1);


        // Optionally, assert the expected behavior
//        User updatedUser = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
//        System.out.println(updatedUser);
//        System.out.println(updatedUser.getProjects());
//        assertEquals(user1Projects, updatedUser.getProjects(), "User's projects should have been updated");
    }

    public Optional<User> getUserWithId(Long userId) {
        return userRepo.findById(userId);
    }
}
