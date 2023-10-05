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

    @Autowired
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

//        userRepo.saveAll(List.of(user1,user2,user3));
        // Save all users

        Project project1 = new Project();
        project1.setProjectName("Calculator");
        Project project2 = new Project();
        project2.setProjectName("Commerce");
        Project project3 = new Project();
        project3.setProjectName("Management");
        project3.setStatus(Status.WORKING);

//        projectRepo.saveAll(List.of(project1,project2,project3));
//         save all projects

        // We cannot give one user(customer) same project , we can give more but not same
        // set project to user via userRepo (update)


            //not set , set is for first time , when we want to update we must get and .add
            // user1 has 2 projects which he must do

            user1.getProjects().add(project1);
            user1.getProjects().add(project2);
//            userRepo.save(user1);

                    userRepo.saveAll(List.of(user1,user2,user3));

            project1.setUser(user1);
            project2.setUser(user1);
//            projectRepo.save(project1);
//            projectRepo.save(project2);

            projectRepo.saveAll(List.of(project1,project2,project3));


//        System.out.println(user1);
//        System.out.println(user1.getProjects());
//        System.out.println(project1.getUser());
        Optional<User> optionalUser = userRepo.findById(1L);
        User user1FromDB = optionalUser.orElseThrow(()-> new RuntimeException("no user "));
        System.out.println(user1FromDB); // In db changes are not visible
        System.out.println(user1FromDB.getProjects());
        System.out.println(project1.getUser());
    }
}
