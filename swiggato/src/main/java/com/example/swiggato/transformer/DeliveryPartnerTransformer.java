package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.DeliveryPartnerRequest;
import com.example.swiggato.model.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerTransformer {
    public static DeliveryPartner DeliveryPartnerRequestToDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .orderEntityList(new ArrayList<>())
                .build();
    }
}
