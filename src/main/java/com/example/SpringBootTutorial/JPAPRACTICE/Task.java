package com.example.SpringBootTutorial.JPAPRACTICE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude =  "projects") // this is important for operations in db
@ToString(exclude = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Status status = Status.NOT_STARTED;

//    However, the ManyToMany association in JPA typically requires a join table to manage the relationship
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
    @JoinTable(name = "task_projects",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects ;
}
