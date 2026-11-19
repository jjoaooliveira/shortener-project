package com.jjoaooliveira.shortener.customer.usecase.user;

import java.util.Objects;

import org.springframework.cache.annotation.CacheEvict;

import com.jjoaooliveira.shortener.customer.domain.Customer;
import com.jjoaooliveira.shortener.customer.domain.Customers;
import com.jjoaooliveira.shortener.customer.usecase.CustomerUseCase;

import jakarta.transaction.Transactional;

public class UpdateCustomerByUsername extends CustomerUseCase {
    public UpdateCustomerByUsername(Customers customers) {
        super(customers);
    }

    @CacheEvict(value = "customers", key = "#username")
    @Transactional
    public void update(UpdateCustomerRequest request, String username) {
        Customer customer = customers.findByUsername(Objects.requireNonNull(username))
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.updateName(request.name());
    }
}
