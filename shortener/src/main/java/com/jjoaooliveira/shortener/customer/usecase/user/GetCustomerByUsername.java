package com.jjoaooliveira.shortener.customer.usecase.user;

import com.jjoaooliveira.shortener.customer.domain.Customers;
import com.jjoaooliveira.shortener.customer.usecase.CustomerDTO;
import com.jjoaooliveira.shortener.customer.usecase.CustomerNotFoundException;
import com.jjoaooliveira.shortener.customer.usecase.CustomerUseCase;

public class GetCustomerByUsername extends CustomerUseCase {

    public GetCustomerByUsername(Customers customers) {
        super(customers);
    }

    public CustomerDTO get(String username) {
        return customers.findByUsername(username)
                .map(CustomerDTO::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(username));
    }
}
