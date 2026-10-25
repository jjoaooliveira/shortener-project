package com.jjoaooliveira.shortener.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jjoaooliveira.shortener.customer.domain.Customer;
import com.jjoaooliveira.shortener.customer.domain.Customers;

@Repository
interface CustomerRepository extends Customers, JpaRepository<Customer, UUID> {

}
