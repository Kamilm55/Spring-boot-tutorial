package com.example.SpringBootTutorial.JPAPRACTICE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t JOIN t.projects  as p WHERE p.id = :projectId")
    Set<Task> getTasksByProjects_Id(Long projectId);
}
