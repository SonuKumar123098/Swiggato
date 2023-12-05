package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.FoodRequest;
import com.example.swiggato.model.Customer;
import com.example.swiggato.model.FoodItem;
import com.example.swiggato.model.MenuItem;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FoodTransformer {

    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest,MenuItem menuItem,Customer customer) {
        return FoodItem.builder()
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .cart(customer.getCart())
                .menu(menuItem)
                .totalCost(foodRequest.getRequiredQuantity()*menuItem.getPrice())
                .build();
    }
}
