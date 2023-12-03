package com.example.swiggato.service;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.RestaurantResponse;

public interface RestaurantService {
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    public String changeOpenedStatus(int id);

    public RestaurantResponse addFoodItemToRestaurant(FoodRequest foodRequest);
}
