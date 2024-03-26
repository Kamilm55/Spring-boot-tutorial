package com.example.SpringBootTutorial.JPAPRACTICE;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "projects") //Learn: This is important for operations in db
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

    //Learn:
    //  @JsonManagedReference and @JsonBackReference are annotations provided by the Jackson library (a popular Java library for JSON processing) to help manage bi-directional relationships in JSON serialization and deserialization. These annotations are useful when you have relationships between entities, and you want to control how these relationships are represented in the JSON output.
    // Consider a scenario where you have a bidirectional relationship between two entities, such as a parent-child relationship.
    // Without proper handling, Jackson might enter into an infinite loop when trying to serialize these entities,
    // resulting in a StackOverflowError. To prevent this, you can use @JsonManagedReference and @JsonBackReference.

    @OneToMany(mappedBy = "user" , cascade = CascadeType.PERSIST)// cascade = CascadeType.ALL  (without orphanRemoval)// if we delete one user it deletes all projects that Fk is this user
    @JsonManagedReference // Use this annotation to control the serialization of the projects field
    private Set<Project> projects = new HashSet<>();


    // FK exception => when i delete user project FK (user_id) set to null
    @PreRemove
    private void preRemove() {
        projects.forEach( project -> project.setUser(null));
    }
}
