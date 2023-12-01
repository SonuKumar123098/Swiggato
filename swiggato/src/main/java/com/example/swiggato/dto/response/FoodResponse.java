package com.example.swiggato.dto.response;
import com.example.swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodResponse {

    String dishName;
    boolean veg;
    double price;
    FoodCategory foodCategory;
}
