//package com.example.SpringBootTutorial.JPAPRACTICE;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//@Data
//@EqualsAndHashCode(exclude = "user") // this is important for operations in db
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "Item")
//public class Item {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//}
