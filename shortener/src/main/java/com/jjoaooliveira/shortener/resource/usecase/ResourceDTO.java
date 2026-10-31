package com.jjoaooliveira.shortener.resource.usecase;

import java.time.Instant;

import com.jjoaooliveira.shortener.resource.domain.Resource;

public record ResourceDTO(Long id, String code, String address, Instant createdAt) {

    public static ResourceDTO fromResource(Resource resource) {
        return new ResourceDTO(
            resource.getId(), 
            resource.getCode(), 
            resource.getAddress(), 
            resource.getCreatedAt()
        );
    }
}
