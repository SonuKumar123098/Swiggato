package com.example.swiggato.controller;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.dto.request.RestaurantRequest;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.dto.response.RestaurantResponse;
import com.example.swiggato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;

    /**
     *
     * @param restaurantService bean of restaurantservice
     */
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService =restaurantService;
    }


    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest ){
        RestaurantResponse restaurantResponse =restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam("id") int id){
        String message= restaurantService.changeOpenedStatus(id);
        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }
    @PostMapping("/add/food")
    public ResponseEntity addFoodItemToRestaurant(@RequestBody FoodRequest foodRequest){
        RestaurantResponse restaurantResponse=restaurantService.addFoodItemToRestaurant(foodRequest);
        return new ResponseEntity(restaurantResponse,HttpStatus.ACCEPTED);
    }
    //get menu of a restaurant
    @GetMapping("/get/menu")
    public ResponseEntity getMenuOfRestaurant(@RequestParam("id") int id){
        List<FoodResponse>menu=restaurantService.getMenuOfRestaurant(id);
        return new ResponseEntity<>(menu,HttpStatus.ACCEPTED);
    }

}
