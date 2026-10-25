package com.jjoaooliveira.shortener.customer.domain;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Name name;

    @Column(unique = true)
    private Email email;

    Customer() {}
    
    Customer(Name name, Email email) {
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
    }

    Customer(UUID id, Name name, Email email) {
        this(name, email);
        this.id = Objects.requireNonNull(id);
    }

    public String getName() {
        return name.value();
    }

    public String getEmail() {
        return email.value();
    }
}
