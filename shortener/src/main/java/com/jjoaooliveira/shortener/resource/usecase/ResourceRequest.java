package com.jjoaooliveira.shortener.resource.usecase;

import java.time.Instant;
import java.util.UUID;

public record ResourceRequest(ResourceDTO resource, UUID customerId, Instant timestamp) {

}
