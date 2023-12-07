package com.example.swiggato.controller;

import com.example.swiggato.dto.response.OrderResponse;
import com.example.swiggato.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    final OrderEntityService orderEntityService;

    @Autowired
    public OrderController(OrderEntityService orderEntityService) {
        this.orderEntityService = orderEntityService;
    }

    @PostMapping("/place/mobile/{mob}")
    public ResponseEntity placeOrder(@PathVariable("mob") String customerMobile){
        try {
            OrderResponse orderResponse=orderEntityService.placeOrder(customerMobile);
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
//     make the api that can cancel the order if order note place before more than 5 minutes
}
