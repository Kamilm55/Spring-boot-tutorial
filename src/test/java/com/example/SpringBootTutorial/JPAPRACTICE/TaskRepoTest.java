package com.example.SpringBootTutorial.JPAPRACTICE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class TaskRepoTest {
    private TaskRepo taskRepo;
    private ProjectRepo projectRepo;
    private  ProjectService projectService;


    @Autowired
    public TaskRepoTest(TaskRepo taskRepo, ProjectRepo projectRepo, ProjectService projectService) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
        this.projectService = projectService;
    }

    @Test
    void createTasks(){
        Task task1 = new Task();
        task1.setTitle("Math operations");
        task1.setStatus(Status.NOT_STARTED);

        Task task2 = new Task();
        task2.setTitle("Model of data");
        task2.setStatus(Status.NOT_STARTED);

        Task task3 = new Task();
        task3.setTitle("Project structure");
        task3.setStatus(Status.WORKING);

        Task task4 = new Task();
        task4.setTitle("Add to basket");
        task4.setStatus(Status.NOT_STARTED);

        //These are in transient state

        taskRepo.saveAll(List.of(task1,task2,task3,task4));
        // Now persistent state

        List<Task> tasks = taskRepo.findAll();

        System.out.println(tasks);
    }

    ////////////// PROJECT-TASK

    // LazyInitializationException -> SOLUTION
    /*
In Spring Data JPA, you typically need to use the @Transactional annotation to fetch lazy-loaded elements. Lazy loading is a technique used to defer the loading of associated entities (e.g., collections) until they are actually accessed. When you access a lazy-loaded collection or property outside of an active transaction, you may encounter a LazyInitializationException because the entity manager session is closed, and the associated data cannot be fetched.
To fetch lazy-loaded elements without encountering this exception, you need to access them within an active transaction. You can do this by adding the @Transactional annotation to the method or class that requires access to the lazy-loaded elements.
    * */

    @Test
    void addTaskToProject(){
        Long taskId = 1L;
        Long prId = 7L;
        Task task1 = taskRepo.findById(taskId).orElseThrow(()-> new RuntimeException( "Not Found task"));


        // save method NOT WORKING

//        because Lazy loading , we must use @transactional fetch task then continue
//        projectService.addTaskToProject(prId,task1); // add in service


        Set<Task> tasks = projectService.getTasksOfProject(prId); // without query
//        Set<Task> tasks = taskRepo.getTasksByProjects_Id(prId); // with query

        System.out.println(tasks);
    }
}