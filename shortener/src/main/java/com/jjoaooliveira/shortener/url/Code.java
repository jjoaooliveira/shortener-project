package com.jjoaooliveira.shortener.url;

import java.util.Objects;

record Code(String code) {
    public Code {
        if (Objects.isNull(code) || code.isBlank()) {
            throw new RuntimeException("Code is null or blank");
        }
    }
}
