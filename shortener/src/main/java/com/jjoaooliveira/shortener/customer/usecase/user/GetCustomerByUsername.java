package com.jjoaooliveira.shortener.customer.usecase.user;

import org.springframework.cache.annotation.Cacheable;

import com.jjoaooliveira.shortener.customer.domain.Customers;
import com.jjoaooliveira.shortener.customer.usecase.CustomerDTO;
import com.jjoaooliveira.shortener.customer.usecase.CustomerNotFoundException;
import com.jjoaooliveira.shortener.customer.usecase.CustomerUseCase;

public class GetCustomerByUsername extends CustomerUseCase {

    public GetCustomerByUsername(Customers customers) {
        super(customers);
    }

    @Cacheable(value = "customers", key = "#username")
    public CustomerDTO get(String username) {
        return customers.findByUsername(username)
                .map(CustomerDTO::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(username));
    }
}
