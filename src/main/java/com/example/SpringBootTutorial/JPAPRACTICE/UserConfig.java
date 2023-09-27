package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Configuration
public class UserConfig implements CommandLineRunner {
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;
    private final TaskRepo taskRepo;

    public UserConfig(UserRepo userRepo, ProjectRepo projectRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setUserName("Kamil");
        user1.setUserEmail("Kamil@gmail.com");
        user2.setUserName("Perviz");
        user2.setUserEmail("Perviz@gmail.com");
        user3.setUserName("Samir");
        user3.setUserEmail("Samir@gmail.com");

        userRepo.saveAll(List.of(user1,user2,user3));
        // Save all users

        Project project1 = new Project();
        project1.setProjectName("Calculator");
        Project project2 = new Project();
        project2.setProjectName("Commerce");
        Project project3 = new Project();
        project3.setProjectName("Management");
        project3.setStatus(Status.WORKING);

        projectRepo.saveAll(List.of(project1,project2,project3));
        // save all projects

        // We cannot give one user(customer) same project , we can give more but not same
        // set project to user via userRepo
        Set<Project> user1Projects = new HashSet<>();
        user1Projects.add(project1 );
        user1Projects.add(project2);
        // user1 has 2 projects which he must do
//        user1.setProjects(user1Projects); // this is not from db // therefore we cannot update with set
        Optional<User> optionalUser = userRepo.findById(user1.getId());
        User user1FromDB = optionalUser.orElseThrow(null);
        if(user1FromDB!=null){
//            userRepo.updateUserByProjects(user1FromDB.getId() , user1Projects);
//            user1FromDB.setProjects(user1Projects); // right form is this obj from db
        projectRepo.updateProjectByUserId(user1FromDB, project1.getId());
            projectRepo.updateProjectByUserId(user1FromDB, project2.getId());
        }

        System.out.println(user1);// @Transactional affects this
        System.out.println(user1FromDB);
        System.out.println(user1FromDB.getProjects()); // In db changes are not visible

    }
}
