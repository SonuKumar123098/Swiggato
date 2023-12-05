package com.example.swiggato.service;

import com.example.swiggato.dto.request.MenuRequest;
import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.MenuResponse;
import com.example.swiggato.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    public String changeOpenedStatus(int id);

    public RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest);

    List<MenuResponse> getMenuOfRestaurant(int id);
}
