package com.example.SpringBootTutorial.JPAPRACTICE;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude =  {"user", "tasks"}) // this is important for operations in db
@ToString(exclude =  {"user", "tasks"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_STARTED;

    @ManyToOne(cascade = CascadeType.PERSIST) // when we use CascadeType.ALL,REMOVE if we delete project it deletes related user , PERSIST => when we save or update it affects related entity
    @JoinColumn(name = "user_id" )
    @JsonBackReference // Use this annotation to break the circular reference when serializing
    private User user;

    @ManyToMany(mappedBy = "projects")
//    In a bidirectional many-to-many relationship between two entities in a Java application, it's a common practice to use @JsonIgnore on one side of the relationship to prevent circular references during JSON serialization. This means that you typically apply @JsonIgnore to one of the collection properties that represents the relationship on one of the entities.
    @JsonIgnore
    //n a bidirectional JPA relationship, when you use the mappedBy attribute in one entity, you're essentially indicating that the relationship is already mapped by the other entity, and you don't need to specify the @JoinColumn because the mapping is defined in the other entity.
    private Set<Task> tasks;

}