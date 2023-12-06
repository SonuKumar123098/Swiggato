package com.example.swiggato.transformer;

import com.example.swiggato.dto.response.CartStatusResponse;
import com.example.swiggato.dto.response.FoodResponse;
import com.example.swiggato.model.Cart;
import com.example.swiggato.model.Customer;
import com.example.swiggato.model.MenuItem;

import java.util.List;

public class CartTransformer {
    public static CartStatusResponse CartToCartStatusResponse(Cart cart, List<FoodResponse>foodResponses, MenuItem menuItem){
        Customer customer=cart.getCustomer();
        return  CartStatusResponse.builder()
                .customerName(customer.getName())
                .customerAddress(customer.getAddress())
                .customerMobile(customer.getMobileNo())
                .cartTotal(cart.getCartTotal())
                .foodList(foodResponses)
                .restaurantName(menuItem.getRestaurant().getName())
                .build();
    }
}
