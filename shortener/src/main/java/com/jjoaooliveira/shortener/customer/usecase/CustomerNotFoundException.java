package com.jjoaooliveira.shortener.customer.usecase;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String username) {
        super("Customer not found with username: " + username);
    }

}
