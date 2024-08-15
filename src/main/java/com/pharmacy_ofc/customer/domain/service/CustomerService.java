package com.pharmacy_ofc.customer.domain.service;

import com.pharmacy_ofc.customer.domain.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    void createCustomer (Customer customer);
    Optional<Customer> findCustomer (String id);
    void updateCustomer (Customer customer);
    void deleteCustomer (String id);
}
