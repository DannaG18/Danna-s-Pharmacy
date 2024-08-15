package com.pharmacy_ofc.customer.application;

import com.pharmacy_ofc.customer.domain.service.CustomerService;

public class DeleteCustomerUseCase {
    private final CustomerService customerService;

    public DeleteCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(String id) {
        customerService.deleteCustomer(id);
    }
}
