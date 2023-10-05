package com.example.SpringBootTutorial.JPAPRACTICE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "projects") // this is important for operations in db
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

    @OneToMany(mappedBy = "user"  , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Project> projects = new HashSet<>();

    @OneToOne(mappedBy = "user"  , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Item item;

}
