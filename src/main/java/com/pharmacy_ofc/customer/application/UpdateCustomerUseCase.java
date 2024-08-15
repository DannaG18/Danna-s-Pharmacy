package com.pharmacy_ofc.customer.application;

import com.pharmacy_ofc.customer.domain.entity.Customer;
import com.pharmacy_ofc.customer.domain.service.CustomerService;

public class UpdateCustomerUseCase {
    private final CustomerService customerService;

    public UpdateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.updateCustomer(customer);
    }
}
