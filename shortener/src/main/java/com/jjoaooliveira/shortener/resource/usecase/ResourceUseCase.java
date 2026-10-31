package com.jjoaooliveira.shortener.resource.usecase;

import java.time.Instant;
import java.util.UUID;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.ResourceFactory;
import com.jjoaooliveira.shortener.resource.domain.Resources;

abstract class ResourceUseCase {
    protected Resources resources;

    public ResourceUseCase(Resources resources) {
        this.resources = resources;
    }

    protected Resource parseRequest(ResourceRequest request) {
        String code = request.resource().code();
        String address = request.resource().address();
        UUID id = request.customerId();
        Instant createdAt = request.timestamp();

        return ResourceFactory.makeResource(code, address, createdAt, id);
    };
}
