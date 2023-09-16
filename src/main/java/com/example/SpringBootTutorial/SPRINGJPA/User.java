package com.example.SpringBootTutorial.SPRINGJPA;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Table(name = "app_users")
@Entity(name = "User")
//In SQL, "user" is often a reserved keyword used for various purposes, and different database systems may treat it differently.
// When you try to create a table with a reserved keyword as the table name, you can encounter syntax errors or other issues.
public class User {
    @Id // Primary Key
    @SequenceGenerator(
            name = "sequence",sequenceName = "sequence",allocationSize = 1
    )
    @GeneratedValue(generator = "sequence" , strategy = SEQUENCE) // import static jakarta.persistence.GenerationType.SEQUENCE;
   @Column(updatable = false )
    private Long id;

    @Column(nullable = false , name = "first_name" , columnDefinition = "text")
    private String firstName;
    @Column(nullable = false ,columnDefinition = "text")
    private String lastName;
    @Column(nullable = false ,columnDefinition = "text" ,unique = true)
    private String email;
    @Column(nullable = false )
    private int age;

    public User(Long id, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
