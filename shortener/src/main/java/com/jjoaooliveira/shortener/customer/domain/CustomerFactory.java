package com.jjoaooliveira.shortener.customer.domain;

public class CustomerFactory {
    public static Customer create(String username, String email, String name) {
        return new Customer(username, new Name(name), new Email(email));
    }
}
