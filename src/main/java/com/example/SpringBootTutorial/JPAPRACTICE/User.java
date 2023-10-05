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

    @OneToMany(mappedBy = "user"  , cascade = CascadeType.ALL/*, fetch = FetchType.EAGER*/)
    @JsonManagedReference // Use this annotation to control the serialization of the projects field
    private Set<Project> projects = new HashSet<>();

//    @OneToOne(mappedBy = "user"  , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Item item;

}
