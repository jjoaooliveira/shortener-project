package com.jjoaooliveira.shortener.customer.usecase.user;

import com.jjoaooliveira.shortener.customer.domain.Customer;
import com.jjoaooliveira.shortener.customer.domain.CustomerFactory;
import com.jjoaooliveira.shortener.customer.domain.Customers;
import com.jjoaooliveira.shortener.customer.usecase.CustomerUseCase;

public class CreateCustomer extends CustomerUseCase {
    public CreateCustomer(Customers customers) {
        super(customers);
    }

    public void create(CreateCustomerRequest request) {
        Customer customer = CustomerFactory.create(request.username(), request.name(), request.email());
        customers.save(customer);
    }
}
