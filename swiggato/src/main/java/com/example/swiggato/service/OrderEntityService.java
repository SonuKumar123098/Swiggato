package com.example.swiggato.service;

import com.example.swiggato.dto.response.OrderResponse;

public interface OrderEntityService {
    OrderResponse placeOrder(String customerMobile);
}
