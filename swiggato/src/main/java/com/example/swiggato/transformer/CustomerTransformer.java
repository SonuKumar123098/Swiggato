package com.example.swiggato.transformer;

import com.example.swiggato.dto.request.CustomerRequest;
import com.example.swiggato.dto.response.CustomerResponse;
import com.example.swiggato.model.Customer;

public class CustomerTransformer {
    public  static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .gender(customerRequest.getGender())
                .email(customerRequest.getEmail())
                .mobileNo(customerRequest.getMobileNo())
                .address(customerRequest.getAddress())
                .build();
    }
    public  static CustomerResponse CustomerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .name(customer.getName())
                .gender(customer.getGender())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .cartId(customer.getCart().getId())
                .build();
    }
}
