package com.jjoaooliveira.shortener.resource.domain;

import java.time.Instant;

public class ResourceFactory {
    public static Resource makeResource(String code, String address, Instant createdAt) {
        return new Resource(
            new Code(code), 
            new Address(address), 
            createdAt
        );
    }
}
