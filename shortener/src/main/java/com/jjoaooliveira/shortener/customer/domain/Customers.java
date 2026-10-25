package com.jjoaooliveira.shortener.customer.domain;

import java.util.Optional;

public interface Customers {
    void save(Customer customer);
    Optional<Customer> findCustomerById(String id);
    Optional<Customer> findCustomerByEmail(String email);
}
