package com.example.swiggato.model;

import com.example.swiggato.Enum.RestaurantCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;
    @Column(unique = true,nullable = false)
    @Size(min = 10, max = 10)
    String contactNumber;
    boolean opened;
    String location;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<OrderEntity>orderEntityList=new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<MenuItem>availableItems=new ArrayList<>();

}
