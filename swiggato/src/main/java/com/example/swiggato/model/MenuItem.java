package com.example.swiggato.model;

import com.example.swiggato.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String dishName;
    double price;
    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;
    boolean veg;
    boolean available;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL)
    List<FoodItem>foodItemList=new ArrayList<>();
}
