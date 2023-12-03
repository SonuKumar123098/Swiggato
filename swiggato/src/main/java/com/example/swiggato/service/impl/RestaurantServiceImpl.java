package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.dto.response.RestaurantResponse;
import com.example.swiggato.exception.RestaurantNotFoundException;
import com.example.swiggato.model.FoodItem;
import com.example.swiggato.model.Restaurant;
import com.example.swiggato.repository.RestaurantRepository;
import com.example.swiggato.service.RestaurantService;
import com.example.swiggato.transformer.FoodItemTransformer;
import com.example.swiggato.transformer.RestaurantTransformer;
import com.example.swiggato.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRepository;
    final ValidationUtils validationUtils;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ValidationUtils validationUtils){
        this.restaurantRepository=restaurantRepository;
        this.validationUtils = validationUtils;
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

    @Override
    public String changeOpenedStatus(int id) {
        //check valide restaurant id
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Invalid Restaurant Id");
        }
        Restaurant restaurant=restaurantRepository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());
        restaurantRepository.save(restaurant);
        if(restaurant.isOpened()){
            return "restaurant opened now!";
        }
        return "restaurant closed!";
    }

    @Override
    public RestaurantResponse addFoodItemToRestaurant(FoodRequest foodRequest) {
        //check restaurant is valid or not
        if(!validationUtils.validateRestaurantId(foodRequest.getRestaurantId())){
            throw new RestaurantNotFoundException("restaurant does not exist!");
        }
        Restaurant restaurant=restaurantRepository.findById(foodRequest.getRestaurantId()).get();
        //prepare foood entity
        FoodItem foodItem= FoodItemTransformer.FoodRequestToFoodItem(foodRequest);
        foodItem.setRestaurant(restaurant);
        restaurant.getAvailableItems().add(foodItem);
        //save both restaurant and food
        Restaurant savedarestaurant=restaurantRepository.save(restaurant);
        //prepare response
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedarestaurant);
    }

    @Override
    public List<FoodResponse> getMenuOfRestaurant(int id) {
        //validate restaurant id
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("invalid restaurant Id!");
        }
        List<FoodResponse>menu=restaurantRepository.findById(id).get().getAvailableItems().stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
        return menu;

    }
}
