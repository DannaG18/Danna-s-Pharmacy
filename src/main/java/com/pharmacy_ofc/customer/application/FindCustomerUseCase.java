package com.pharmacy_ofc.customer.application;

import com.pharmacy_ofc.customer.domain.entity.Customer;
import com.pharmacy_ofc.customer.domain.service.CustomerService;
import java.util.Optional;

public class FindCustomerUseCase {
    private final CustomerService customerService;

    public FindCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<Customer> execute(String id) {
        return customerService.findCustomer(id);
    }
}
