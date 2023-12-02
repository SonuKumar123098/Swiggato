package com.example.swiggato.dto.response;

import com.example.swiggato.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {
     String name;
//     RestaurantCategory restaurantCategory;
     String contactNumber;
     boolean opened;
     String location;
     List<FoodResponse>menu;
}
