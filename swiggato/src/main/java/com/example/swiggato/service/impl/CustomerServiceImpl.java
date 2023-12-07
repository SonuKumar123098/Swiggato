package com.example.swiggato.service.impl;

import com.example.swiggato.dto.request.CustomerRequest;
import com.example.swiggato.dto.response.CustomerResponse;
import com.example.swiggato.exception.CustomerNotFoundException;
import com.example.swiggato.model.Cart;
import com.example.swiggato.model.Customer;
import com.example.swiggato.repository.CustomerRepository;
import com.example.swiggato.service.CustomerService;
import com.example.swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        //dto->customer Model
        Customer customer= CustomerTransformer.CustomerRequestToCustomer(customerRequest);
        //allocate a cart to customer
        Cart cart= Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
        customer.setCart(cart);
        //save both customer and cart
        Customer savedCustomer= customerRepository.save(customer);
        //prepare customer response
        CustomerResponse customerResponse=CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
        return customerResponse;
    }

    public CustomerResponse findCustomerByMobile(String mobileNo)  {
        Customer customer=customerRepository.findByMobileNo(mobileNo);
        if(customer==null){
            throw new CustomerNotFoundException("customer not found");
        }
        CustomerResponse customerResponse=CustomerTransformer.CustomerToCustomerResponse(customer);
        return customerResponse;
    }
}
