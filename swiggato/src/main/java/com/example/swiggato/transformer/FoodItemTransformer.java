package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.model.FoodItem;

import java.util.List;

public class FoodItemTransformer {
    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem){
        return FoodResponse.builder()
                .dishName(foodItem.getDishName())
                .foodCategory(foodItem.getFoodCategory())
                .price(foodItem.getPrice())
                .veg(foodItem.isVeg())
                .build();
    }
    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest){
        return FoodItem.builder()
                .dishName(foodRequest.getDishName())
                .price(foodRequest.getPrice())
                .veg(foodRequest.isVeg())
                .available(foodRequest.isAvailable())
                .foodCategory(foodRequest.getFoodCategory())
                .build();
    }
}
