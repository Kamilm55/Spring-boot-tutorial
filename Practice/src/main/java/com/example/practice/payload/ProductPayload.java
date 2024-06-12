package com.example.practice.payload;

import lombok.*;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPayload {
    private String name;
    private String code;
    private int quantity;
    private double price;
}
