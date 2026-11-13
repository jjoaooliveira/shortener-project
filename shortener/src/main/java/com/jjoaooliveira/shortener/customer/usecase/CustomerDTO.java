package com.jjoaooliveira.shortener.customer.usecase;

import com.jjoaooliveira.shortener.customer.domain.Customer;

public record CustomerDTO(String username, String name, String email) {
    public static CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(customer.getUsername(), customer.getName(), customer.getEmail());
    }
}
