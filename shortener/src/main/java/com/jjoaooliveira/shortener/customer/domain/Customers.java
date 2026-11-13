package com.jjoaooliveira.shortener.customer.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Customers extends JpaRepository<Customer, String> {
    Optional<Customer> findByUsername(String username);
}
