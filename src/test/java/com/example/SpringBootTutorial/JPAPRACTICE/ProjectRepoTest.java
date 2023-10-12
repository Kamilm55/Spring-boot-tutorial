package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager="txMgr", defaultRollback=false)
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
        Project p1 = projectRepo.findById(4L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));

        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
        projectRepo.updateProjectByUserId(user1, p1.getId());

        Project updatedProject =  projectRepo.findById(4L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));
        assertEquals(updatedProject.getUser(), user1 , "User is not updated");

    }

    @Test
    void updateProjectByProjectStatusAndName() {
        Project p1 = projectRepo.findById(4L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));
        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
//       p1.setStatus(Status.COMPLETED);
//       p1.setProjectName("NewProject");
//       projectRepo.save(p1);
//  This method not working because @Transactional not working in test but it works in service and controller or in repo


        System.out.println(user1.getProjects());
//      that's why i use @Transactional  in repo and update value
       projectRepo.updateProjectStatusAndNameById(p1.getId(),"NewProject",Status.COMPLETED);

        User updatedUser = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
        Project updatedProject =  projectRepo.findById(4L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));


//        System.out.println(updatedUser.getProjects().contains(updatedProject));
//        System.out.println(updatedUser.getProjects());
//        assertEquals(true, updatedUser.getProjects().contains(updatedProject), "Project is not updated");

    }

    @Test
    void deleteProjectWithId(){
        Long projectId = 4L;

        projectRepo.deleteById(projectId);

    }
}