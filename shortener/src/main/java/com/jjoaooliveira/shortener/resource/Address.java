package com.jjoaooliveira.shortener.resource;

import java.util.Objects;

record Address(String value) {
    public Address {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Address value is null or blank");
        }
    }
}
