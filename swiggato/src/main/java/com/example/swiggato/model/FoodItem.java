package com.example.swiggato.model;

import com.example.swiggato.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "food_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String dishName;
    double price;
    boolean veg;
    boolean available;
    @Enumerated(value = EnumType.STRING)
    FoodCategory foodCategory;
//    int requiredQuantity;
//    double totalCost;

    @ManyToOne
    @JoinColumn
    OrderEntity order;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn
    Cart cart;

//    @ManyToOne
//    @JoinColumn
//    MenuItem menuItem;
}
