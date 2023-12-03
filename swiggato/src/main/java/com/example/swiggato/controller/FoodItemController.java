package com.example.swiggato.controller;

import com.example.swiggato.Enum.FoodCategory;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    final FoodItemService foodItemService;
    @Autowired
    public FoodItemController(FoodItemService foodItemService){
        this.foodItemService=foodItemService;
    }
    // get all food of a particular category
    @GetMapping("/get/category/{id}")
    public ResponseEntity getFoodByCategory(@PathVariable("id") FoodCategory foodCategory){
        List<FoodResponse>foodResponses=foodItemService.getFoodByCategory(foodCategory);
        return new ResponseEntity<>(foodResponses, HttpStatus.ACCEPTED);
    }
    //get all MAIN-COURSE item with price above x rupees from a particular restaurant
    @GetMapping("/above/price")
    public ResponseEntity getFoodOfAtLeastPrice(@RequestParam("p") double price){
        List<FoodResponse>foodResponses=foodItemService.getFoodOfAtLeastPrice(price);
        return new ResponseEntity(foodResponses,HttpStatus.FOUND);
    }
    //get all veg food a restaurant

    // get all non veg food of a restaurant
    //get 5 cheapest item of a particular restaurant
    //get 5 costliest item of a particular restaurant-> name of dish and rest that serve dish

}
