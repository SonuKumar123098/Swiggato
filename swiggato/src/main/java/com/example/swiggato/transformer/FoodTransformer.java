package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.model.Customer;
import com.example.swiggato.model.FoodItem;
import com.example.swiggato.model.MenuItem;

public class FoodTransformer {

    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest,MenuItem menuItem) {
        return FoodItem.builder()
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .menu(menuItem)
                .totalCost(foodRequest.getRequiredQuantity()*menuItem.getPrice())
                .build();
    }
    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem){
        return FoodResponse.builder()
                .foodCategory(foodItem.getMenu().getFoodCategory())
                .veg(foodItem.getMenu().isVeg())
                .price(foodItem.getMenu().getPrice())
                .dishName(foodItem.getMenu().getDishName())
                .quantityAdded(foodItem.getRequiredQuantity())
                .build();
    }
}
