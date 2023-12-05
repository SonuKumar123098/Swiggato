package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.dto.response.DeliveryPartnerResponse;
import com.example.swiggato.repository.DeliveryPartnerRepository;
import com.example.swiggato.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    @Override
    public DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        return null;
    }
}
