package com.example.swiggato.service;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.response.CartStatusResponse;

public interface CartService {
    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest);
}
