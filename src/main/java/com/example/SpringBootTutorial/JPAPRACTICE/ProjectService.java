package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProjectService {
private ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }


    @Transactional
    public Set<Task> getTasksOfProject(Long prId ) {
        Project p1 =  projectRepo.findById(prId).orElseThrow(() -> new RuntimeException("Project  not found"));

        return  p1.getTasks();
    }

    @Transactional
    public void addTaskToProject(Long prId, Task task1) {
        Project p1 =  projectRepo.findById(prId).orElseThrow(() -> new RuntimeException("Project  not found"));
         p1.getTasks().add(task1);
         projectRepo.save(p1);
    }
}
