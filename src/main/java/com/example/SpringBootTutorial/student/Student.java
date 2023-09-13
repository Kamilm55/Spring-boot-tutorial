package com.example.SpringBootTutorial.student;

import java.time.LocalDate;

public class Student {
    private long id;
    private String name;
    private String email;
    private LocalDate birth_of_date;
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
