package com.example.swiggato.transformer;

import com.example.swiggato.dto.response.CartStatusResponse;
import com.example.swiggato.model.Cart;
import com.example.swiggato.model.Customer;

import java.util.ArrayList;

public class CartTransformer {
    public static CartStatusResponse CartToCartStatusResponse(Cart cart){
        Customer customer=cart.getCustomer();
        return  CartStatusResponse.builder()
                .cartTotal(cart.getCartTotal())
                .customerAddress(customer.getAddress())
                .customerMobile(customer.getMobileNo())
                .customerName(customer.getName())
                .restaurantName(cart.getFoodItems().get(0).getMenu().getRestaurant().getName())
                .build();
    }
}
