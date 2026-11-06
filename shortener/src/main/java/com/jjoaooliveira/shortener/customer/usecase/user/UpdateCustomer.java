package com.jjoaooliveira.shortener.customer.usecase.user;

import com.jjoaooliveira.shortener.customer.domain.Customer;
import com.jjoaooliveira.shortener.customer.domain.Customers;
import com.jjoaooliveira.shortener.customer.usecase.CustomerUseCase;

import jakarta.transaction.Transactional;

public class UpdateCustomer extends CustomerUseCase {
    public UpdateCustomer(Customers customers) {
        super(customers);
    }

    @Transactional
    public void update(UpdateCustomerRequest request) {
        Customer customer = customers.findByUsername(request.username())
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.updateName(request.name());
    }
}
