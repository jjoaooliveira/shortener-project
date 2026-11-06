package com.jjoaooliveira.shortener.customer.domain;

import java.util.Objects;

import org.hibernate.annotations.EmbeddedColumnNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @Column(updatable = false, nullable = false)
    private String username;

    @Embedded
    @EmbeddedColumnNaming("name")
    @Column(nullable = false, length = 255)
    private Name name;

    @Embedded
    @EmbeddedColumnNaming("email")
    @Column(nullable = false, unique = true, length = 255)
    private Email email;

    Customer() {}

    Customer(String username, Name name, Email email) {
        this.username = Objects.requireNonNull(username);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name.value();
    }

    public void updateName(String newName) {
        this.name = new Name(newName);
    }

    public String getEmail() {
        return email.value();
    }
}
