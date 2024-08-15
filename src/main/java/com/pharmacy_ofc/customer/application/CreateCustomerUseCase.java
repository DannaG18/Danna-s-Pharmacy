package com.pharmacy_ofc.customer.application;

import com.pharmacy_ofc.customer.domain.entity.Customer;
import com.pharmacy_ofc.customer.domain.service.CustomerService;

public class CreateCustomerUseCase {
    private final CustomerService customerService;

    public CreateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.createCustomer(customer);
    }
}
