package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.RestaurantResponse;
import com.example.swiggato.model.Restaurant;
import com.example.swiggato.repository.RestaurantRepository;
import com.example.swiggato.service.RestaurantService;
import com.example.swiggato.transformer.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }
    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        // dto->restaurant
        Restaurant restaurant= RestaurantTransformer.RestaurantResponseToRestaurant(restaurantRequest);
        // persist/save the model in the db
        Restaurant savesRestaurant= restaurantRepository.save(restaurant);
        // prepare response from model and return response
        return RestaurantTransformer.RestaurantToRestaurantResponse(savesRestaurant);
    }
}
