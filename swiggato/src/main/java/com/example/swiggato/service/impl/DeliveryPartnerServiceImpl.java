package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.dto.response.DeliveryPartnerResponse;
import com.example.swiggato.model.DeliveryPartner;
import com.example.swiggato.repository.DeliveryPartnerRepository;
import com.example.swiggato.service.DeliveryPartnerService;
import com.example.swiggato.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    @Override
    public String addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        // check that given mobile number is already exist or not
        DeliveryPartner deliveryPartner=deliveryPartnerRepository.findByMobileNo(deliveryPartnerRequest.getMobileNo());
        if(deliveryPartner.equals(null)) return "Already registered, please sign in!";
        DeliveryPartner deliveryPartner1= DeliveryPartnerTransformer.DeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);
        DeliveryPartner savedDeliveryPartner=deliveryPartnerRepository.save(deliveryPartner1);
        return "Your registration is successfull";
    }
}
