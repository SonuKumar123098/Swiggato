package com.example.swiggato.dto.request;

import com.example.swiggato.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequest {
    String name;
    RestaurantCategory restaurantCategory;
    String contactNumber;
    String location;
}
