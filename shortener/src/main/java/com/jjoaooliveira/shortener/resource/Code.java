package com.jjoaooliveira.shortener.resource;

import java.util.Objects;

record Code(String value) {
    public Code {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Code value is null or blank");
        }
    }
}
