package com.example.swiggato.service;

import com.example.swiggato.Enum.FoodCategory;
import com.example.swiggato.dto.response.FoodResponse;

import java.util.List;

public interface FoodItemService {
    List<FoodResponse> getFoodByCategory(FoodCategory foodCategory);

    List<FoodResponse> getFoodOfAtLeastPrice(double price);
}
