package com.example.swiggato.dto.response;
import com.example.swiggato.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuResponse {
    String dishName;
    double price;
    FoodCategory foodCategory;
    boolean veg;

}
