package com.jjoaooliveira.shortener.resource.domain;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
record Address(String value) {
    public Address {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Address value is null or blank");
        }
    }
}
