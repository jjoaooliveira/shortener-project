package com.jjoaooliveira.shortener.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjoaooliveira.shortener.customer.domain.Customer;
import com.jjoaooliveira.shortener.customer.domain.Customers;

interface CustomerRepository extends Customers, JpaRepository<Customer, String> {

}
