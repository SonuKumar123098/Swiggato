package com.example.swiggato.service;

import com.example.swiggato.dto.request.CustomerRequest;
import com.example.swiggato.dto.response.CustomerResponse;

public interface CustomerService {
    public CustomerResponse addCustomer(CustomerRequest customerRequest);
    public CustomerResponse findCustomerByMobile(String mobileNo);
}
