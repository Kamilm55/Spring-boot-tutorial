package com.example.SpringBootTutorial.JPAPRACTICE;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Status status = Status.NOT_STARTED;

    @ManyToOne()
    @JoinColumn(name = "user_id" )
    @JsonBackReference // Use this annotation to break the circular reference when serializing
    private User user;

    @ManyToMany(mappedBy = "projects" /*, fetch = FetchType.LAZY*/)
 //n a bidirectional JPA relationship, when you use the mappedBy attribute in one entity, you're essentially indicating that the relationship is already mapped by the other entity, and you don't need to specify the @JoinColumn because the mapping is defined in the other entity.
    private Set<Task> tasks;
}
