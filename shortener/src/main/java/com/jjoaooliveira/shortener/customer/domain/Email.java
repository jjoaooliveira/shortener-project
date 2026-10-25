package com.jjoaooliveira.shortener.customer.domain;

import java.util.Objects;

record Email(String value) {
    Email {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Email value is null or empty");
        }
    }
}
