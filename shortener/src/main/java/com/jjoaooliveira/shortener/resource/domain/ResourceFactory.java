package com.jjoaooliveira.shortener.resource.domain;

import java.time.Instant;
import java.util.UUID;

public class ResourceFactory {
    public static Resource makeResource(String code, String address, Instant createdAt, UUID customerId) {
        return new Resource(
            new Code(code), 
            new Address(address), 
            createdAt,
            customerId
        );
    }
}
