package com.example.swiggato.service.impl;

import com.example.swiggato.Enum.FoodCategory;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.repository.FoodItemRepository;
import com.example.swiggato.service.FoodItemService;
import com.example.swiggato.transformer.FoodItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    final FoodItemRepository foodItemRepository;
    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public List<FoodResponse> getFoodByCategory(FoodCategory foodCategory) {
        List<FoodResponse>foodResponses=foodItemRepository.findAlByFoodCategory(foodCategory).stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
        return foodResponses;
    }

    @Override
    public List<FoodResponse> getFoodOfAtLeastPrice(double price) {
        List<FoodResponse>foodResponses=foodItemRepository.findAll().stream()
                .filter(foodItem -> foodItem.getPrice()>= price)
                .collect(Collectors.toList()).stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
        return foodResponses;
    }
}
