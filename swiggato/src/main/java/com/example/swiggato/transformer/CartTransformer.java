package com.example.swiggato.transformer;

import com.example.swiggato.dto.response.CartResponse;
import com.example.swiggato.model.Cart;

import java.util.ArrayList;

public class CartTransformer {
    public static CartResponse CartToCartResponse(Cart cart){
        return  CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(new ArrayList<>())
                .build();
    }
}
