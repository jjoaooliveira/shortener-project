package com.jjoaooliveira.shortener.customer.domain;

public class CustomerFactory {
    public static Customer create(String name, String email) {
        return new Customer(
            new Name(name), 
            new Email(email)
        );
    }
}
