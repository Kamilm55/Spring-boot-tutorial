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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private Status status = Status.NOT_STARTED;

    @ManyToOne(cascade = CascadeType.ALL/*,optional = false*/)//optional = false: This means that a Project entity must always have a reference to a User entity.
    @JoinColumn(name = "user_id" )
    private User user;

    @ManyToMany(mappedBy = "projects" , fetch = FetchType.LAZY)
//    @JoinColumn(name = "task_id") //n a bidirectional JPA relationship, when you use the mappedBy attribute in one entity, you're essentially indicating that the relationship is already mapped by the other entity, and you don't need to specify the @JoinColumn because the mapping is defined in the other entity.
    private Set<Task> tasks;
}