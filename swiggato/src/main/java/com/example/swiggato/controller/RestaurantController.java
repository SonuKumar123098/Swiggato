package com.example.swiggato.controller;

import com.example.swiggato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;

    /**
     *
     * @param restaurantService bean of restaurantservice
     */
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService =restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody )
}
