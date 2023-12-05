package com.example.swiggato.controller;

import com.example.swiggato.Enum.FoodCategory;
import com.example.swiggato.dto.response.MenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    // get all food of a particular category

    //get all MAIN-COURSE item with price above x rupees from a particular restaurant

    //get all veg food a restaurant

    // get all non veg food of a restaurant
    //get 5 cheapest item of a particular restaurant
    //get 5 costliest item of a particular restaurant
    // Get costliest 5 food items of a particular category-> name of dish and rest that serve dish

}
