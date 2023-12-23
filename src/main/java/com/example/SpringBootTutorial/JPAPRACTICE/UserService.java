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

    @Transactional
    public Set<Project> getProjectsWithUserId(Long uid){
        User user1 = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        return user1.getProjects() ;
    }
    @Transactional
    public void getProjectsWithUserIdOnlyPrint(Long uid){
        User user1 = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        System.out.println(user1.getProjects());
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
        User user1 = userRepo.findById(8L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        // Fetch two distinct projects
        Project p1 = projectRepo.findById(1L).orElseThrow(() -> new RuntimeException("Project with ID 1 not found"));
        Project p2 = projectRepo.findById(8L).orElseThrow(() -> new RuntimeException("Project with ID 2 not found"));

        // Create a set of projects
        Set<Project> user1Projects = new HashSet<>();
        user1Projects.add(p1);
        user1Projects.add(p2);


        // Update the user's projects with query
//        userRepo.updateUserByProjects(user1.getId(), user1Projects);



        System.out.println(user1);

        p1.setUser(user1); // first we must set user to projects
        p2.setUser(user1);

        //then save these via userRepo
        // this must be save before , because projects of user is empty , if we run at the end it updates user as empty set
        // if we run this before  and then run project updates its cascade affect and updates also user
        System.out.println("First save this user");
//        userRepo.save(user1);
        System.out.println(user1);

        System.out.println("Secondly save these projects and update");
        //then save these via projectRepo
        System.out.println(user1Projects);
        projectRepo.saveAll(user1Projects);

//        user1.setProjects(user1Projects); // then projects to user




        // Optionally, assert the expected behavior
//        User updatedUser = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
//        System.out.println(updatedUser);
//        System.out.println(updatedUser.getProjects());
//        assertEquals(user1Projects, updatedUser.getProjects(), "User's projects should have been updated");
    }

    public Optional<User> getUserWithId(Long userId) {
        return userRepo.findById(userId);
    }

    public Set<Project> getUserProjectsWithId(Long userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        User user1FromDB = optionalUser.orElse(null);

        if(user1FromDB!=null){
            System.out.println(user1FromDB.getProjects());
            Class<?> type = user1FromDB.getProjects().getClass();
            System.out.println("Type of projects: " + type.getName());
            System.out.println(user1FromDB.getProjects().getClass());
            return  user1FromDB.getProjects();
        }

        return new HashSet<>();
    }

    public void deleteUserWithId(Long userId) {
        userRepo.deleteById(userId);
    }
}
