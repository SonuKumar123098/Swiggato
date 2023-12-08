package com.example.swiggato.service.impl;

import com.example.swiggato.model.Cart;
import com.example.swiggato.repository.FoodItemRepository;
import com.example.swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemServiceImpl implements FoodItemService {
    final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

//    @Override
//    public void deleteFood() {
//        foodItemRepository.deleteFood();
//    }
}
