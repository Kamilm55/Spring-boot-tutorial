package com.example.SpringBootTutorial.student;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // for hibernate
@Table
public class Student {
    @Id
    @SequenceGenerator(name = "sequence_generator" , sequenceName = "sequence_generator" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "sequence_generator")
    private long id;
    @Column(nullable = false) // This column does not allow null values
    private String name;
    private String email;
    @Column(nullable = false)
    private LocalDate birth_of_date;
    @Column(nullable = false)
    private int age;

    public Student() {}
    public Student(String name, String email, LocalDate birth_of_date, int age) {
        this.name = name;
        this.email = email;
        this.birth_of_date = birth_of_date;
        this.age = age;
    }

    public Student(long id, String name, String email, LocalDate birth_of_date, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth_of_date = birth_of_date;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth_of_date() {
        return birth_of_date;
    }

    public void setBirth_of_date(LocalDate birth_of_date) {
        this.birth_of_date = birth_of_date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
