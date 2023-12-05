package com.example.swiggato.model;

import com.example.swiggato.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String dishName;
    double price;
    boolean veg;
    boolean available;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "food_category")
    FoodCategory foodCategory;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    List<FoodItem>foodItems=new ArrayList<>();
}
