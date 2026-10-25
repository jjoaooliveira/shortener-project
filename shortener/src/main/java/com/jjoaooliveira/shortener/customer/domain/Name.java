package com.jjoaooliveira.shortener.customer.domain;

import java.util.Objects;

record Name(String value) {
    Name {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Name value is null or blank");
        }
    }
}
