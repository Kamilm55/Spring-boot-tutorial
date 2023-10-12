package com.example.SpringBootTutorial.JPAPRACTICE;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "projects") // this is important for operations in db
@ToString(exclude = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.PERSIST)// cascade = CascadeType.ALL  (without orphanRemoval)// if we delete one user it deletes all projects that Fk is this user
    @JsonManagedReference // Use this annotation to control the serialization of the projects field
    private Set<Project> projects = new HashSet<>();


    // FK exception => when i delete user project FK (user_id) set to null
    @PreRemove
    private void preRemove() {
        projects.forEach( project -> project.setUser(null));
    }
}
