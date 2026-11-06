package com.jjoaooliveira.shortener.customer.usecase;

import com.jjoaooliveira.shortener.customer.domain.Customers;

public abstract class CustomerUseCase {
    protected final Customers customers;

    public CustomerUseCase(Customers customers) {
        this.customers = customers;
    }
}
