package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    @Modifying
    @Transactional
    @Query("update Project p set p.user = :user where p.id = :projectId")
    void updateProjectByUserId(User user, Long projectId);


    @Transactional
    @Modifying
    @Query("update Project p set p.projectName = :newProjectName , p.status = :projectStatus where p.id = :projectId")
    void updateProjectStatusAndNameById(@Param("projectId") Long projectId, @Param("newProjectName") String newProjectName,Status projectStatus);


    Set<Project> getProjectsByUserId(Long uid);

    @Modifying
    @Transactional
    @Query("update Project p set p.user = null where p.id = :prId ")
    void deleteUserReferenceInProject(Long prId);


    @Query(" ")
    User getUserReferenceWithProjectId(Long prId);
}
