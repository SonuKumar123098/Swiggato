package com.example.swiggato.repository;

import com.example.swiggato.dto.response.CustomerResponse;
import com.example.swiggato.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobileNo(String mobileNo);
}
