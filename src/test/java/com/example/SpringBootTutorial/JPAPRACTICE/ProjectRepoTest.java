package com.example.SpringBootTutorial.JPAPRACTICE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Set;

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
        Project p1 = projectRepo.findById(3L).orElseThrow(() -> new RuntimeException("Project with ID 3 not found"));

        User user1 = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
        projectRepo.updateProjectByUserId(user1, p1.getId());

        Project updatedProject =  projectRepo.findById(3L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));
        assertEquals(updatedProject.getUser(), user1 , "User is not updated");

    }

    @Test
    void updateProjectByProjectStatusAndName() {
        Project p1 = projectRepo.findById(1L).orElseThrow(() -> new RuntimeException("Project with ID  not found"));
//       p1.setStatus(Status.COMPLETED);
//       p1.setProjectName("NewProject");
//       projectRepo.save(p1);
//  This method not working because @Transactional not working in test but it works in service and controller or in repo

//      that's why i use @Transactional  in repo and update value
        projectRepo.updateProjectStatusAndNameById(p1.getId(),"updated 2 ",Status.NOT_STARTED);

        ///////////// for lazy loading these not working

//        User updatedUser = userRepo.findById(1L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
//        Project updatedProject =  projectRepo.findById(3L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));

//        System.out.println(updatedUser.getProjects().contains(updatedProject));
//        System.out.println(updatedUser.getProjects());
//        assertEquals(true, updatedUser.getProjects().contains(updatedProject), "Project is not updated");

    }
    @Test
    void getProjectsWithUserId(){
        User user1 = userRepo.findById(2L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));

        Set<Project> user1Projects =  projectRepo.getProjectsByUserId(user1.getId());

        // If collection is lazily initialized in entity we can access it only transaction (with query (not with.getProjects()))
        // because it is not fetched from db it will fetch when it is required
        // if it is eager we can access without explicitly defined query in repo

        System.out.println(user1Projects);
    }

    @Test
    void deleteProjectWithId(){
        Project p1 =  projectRepo.findById(2L).orElseThrow(() -> new RuntimeException("Project with ID 4 not found"));
        Long relatedUserId = p1.getUser().getId();

        projectRepo.deleteById(p1.getId());

//        User updatedUser = userRepo.findById(relatedUserId).orElseThrow(() -> new RuntimeException("User  not found"));

//        System.out.println(updatedUser.getProjects());

        // this is for fetch type eager
    }
    @Test
    void deleteUserReferenceInProject(){
        User user1 = userRepo.findById(2L).orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
        Set<Project> projects = projectRepo.getProjectsByUserId(user1.getId());
        Iterator<Project> iterator = projects.iterator();

        Long prId = null;
        if (iterator.hasNext()) {
            Project firstProject = iterator.next();
            prId = firstProject.getId(); // Assuming 'id' is the field you want to retrieve
        }
        if (prId!=null){
            projectRepo.deleteUserReferenceInProject(prId);
        }

    }

    @Test
    void getUserReferenceWithProjectId(){
        Long prId = 1L;

//        Project p1 =  projectRepo.findById(prId).orElseThrow(() -> new RuntimeException("Project  not found"));

//         User userReference = projectRepo.getUserReferenceWithProjectId(prId);
//        System.out.println(p1.getUser());
        System.out.println("p1");
    }
}