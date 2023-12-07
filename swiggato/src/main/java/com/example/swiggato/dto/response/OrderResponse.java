package com.example.swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String orderId;//UUID
    double orderTotal;
    Date orderTime;
    String customerName;
    String customerMobile;
    String customerAddress;
    String deliveryPartnerName;
    String deliveryPartnerMobile;
    String restaurantName;
    String restaurantLocation;
    String restaurantMobile;
    List<FoodResponse>foodList;
}
