package com.example.swiggato.controller;

import com.example.swiggato.dto.request.CustomerRequest;
import com.example.swiggato.dto.response.CustomerResponse;
import com.example.swiggato.service.CustomerService;
import com.example.swiggato.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;//field injection
    /*
   Consturctor Injection ----> Always use in enterprise applications
    */
//     final CustomerService customerService;
//     @Autowired
//     public CustomerController(CustomerService customerService){
//         this.customerService=customerService;
//     }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse=customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }
    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity findCustomerByMobile(@PathVariable("mobile") String mobileNo){
        try{
            CustomerResponse customerResponse = customerService.findCustomerByMobile(mobileNo);
            return new ResponseEntity<>(customerResponse,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
