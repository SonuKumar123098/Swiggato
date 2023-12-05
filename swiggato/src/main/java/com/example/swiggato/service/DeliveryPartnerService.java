package com.example.swiggato.service;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.dto.response.DeliveryPartnerResponse;

public interface DeliveryPartnerService {
    DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest);
}
