package com.example.swiggato.controller;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.dto.response.DeliveryPartnerResponse;
import com.example.swiggato.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partner")
public class DeliveryPartnerController {

    final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/")
    public ResponseEntity addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest){
        DeliveryPartnerResponse deliveryPartnerResponse=deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(deliveryPartnerResponse, HttpStatus.CREATED);
    }
    // give delivery partner with highest number of deliveries
    // send an email to all the partners who have done less than 10 delivers
}
