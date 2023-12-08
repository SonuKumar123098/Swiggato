package com.example.swiggato.controller;

import com.example.swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodItemController {
    final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }
//    @DeleteMapping("/delete")
//    public ResponseEntity deleteFood(){
//        foodItemService.deleteFood();
//        return new ResponseEntity<>("food deleted success fully where cart is null", HttpStatus.ACCEPTED);
//    }
    //give the food category which is ordered most
}
