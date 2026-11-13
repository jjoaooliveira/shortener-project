package com.jjoaooliveira.shortener.customer.usecase;

import org.springframework.stereotype.Component;

import com.jjoaooliveira.shortener.customer.domain.Customer;
import com.jjoaooliveira.shortener.customer.domain.CustomerFactory;
import com.jjoaooliveira.shortener.customer.domain.Customers;

@Component 
public class CustomerService {
    private final Customers customers;

    public CustomerService(Customers customers) {
        this.customers = customers;
    }

    public void create(String username, String name, String email) {
        Customer customer = CustomerFactory.create(username, name, email);
        customers.save(customer);
    }
}
