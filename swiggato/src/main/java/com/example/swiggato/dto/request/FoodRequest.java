package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodRequest {
    String dishName;
    double price;
    boolean veg;
    boolean available;
    FoodCategory foodCategory;
    int restaurantId;
}
