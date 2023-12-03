package com.example.swiggato.controller;

import com.example.swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("foodItem")
public class FoodItemController {

    final FoodItemService foodItemService;
    @Autowired
    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService=foodItemService;
    }
    // get all food of a particular category
    //get all MAIN-COURSE item with price above x rupees from a particular restaurant
    //get all veg food a restaurant
    // get all non veg food of a restaurant
    //get 5 cheapest item of a particular restaurant
    //get 5 costliest item of a particular restaurant-> name of dish and rest that serve dish

}
