package com.example.practice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product /*implements Serializable */{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String code;
    private int quantity;
    private double price;
}
