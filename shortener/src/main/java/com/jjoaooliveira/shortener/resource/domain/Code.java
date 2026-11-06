package com.jjoaooliveira.shortener.resource.domain;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
record Code(String value) {
    public Code {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Code value is null or blank");
        }
    }
}
