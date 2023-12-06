package com.example.swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartStatusResponse {
    String customerName;
    String customerAddress;
    String customerMobile;
    double cartTotal;
    List<FoodResponse>foodList;
    String restaurantName;
}
