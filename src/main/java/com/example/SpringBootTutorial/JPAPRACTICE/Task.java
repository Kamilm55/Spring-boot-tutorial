package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
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

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinColumn(name = "project_id")
    private Set<Project> projects ;
}
