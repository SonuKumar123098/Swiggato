package com.example.swiggato.service;

import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.RestaurantResponse;

public interface RestaurantService {
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);
}
