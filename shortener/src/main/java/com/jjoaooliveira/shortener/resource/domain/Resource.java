package com.jjoaooliveira.shortener.resource.domain;

import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.EmbeddedColumnNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @EmbeddedColumnNaming("code")
    @Column(nullable = false, unique = true, length = 8)
    private Code code;

    @Embedded
    @EmbeddedColumnNaming("address")
    @Column(nullable = false)
    private Address address;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false, updatable = false)
    private String customerId;

    Resource() {}

    Resource(Code code, Address address, Instant createdAt, String customerId) {
        this.code = Objects.requireNonNull(code, "Resource code is null");
        this.address = Objects.requireNonNull(address, "Resource address is null");
        this.createdAt = Objects.requireNonNull(createdAt, "Resource createdAt is null");
        this.customerId = Objects.requireNonNull(customerId, "Resource customerId is null");
    }

    Resource(Long id, Code code, Address address, Instant createdAt, String customerId) {
        this(code, address, createdAt, customerId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code.value();
    }
    
    public String getAddress() {
        return address.value();
    }

    public void updateAddress(String newAddress) {
        this.address = new Address(newAddress);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }
}
