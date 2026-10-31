package com.jjoaooliveira.shortener.resource.usecase;

import java.util.UUID;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;

import jakarta.transaction.Transactional;

public class UpdateResourceAddress extends ResourceUseCase {

    public UpdateResourceAddress(Resources resources) {
        super(resources);
    }

    @Transactional
    ResourceDTO update(ResourceRequest request) {
        ResourceDTO resourceDTO = request.resource();
        String code = resourceDTO.code();
        UUID customerId = request.customerId();

        Resource resourceToUpdate = resources
            .findByCodeAndCustomerId(code, customerId)
            .orElseThrow(RuntimeException::new);
        
        resourceToUpdate.updateAddress(resourceDTO.address());

        return ResourceDTO.fromResource(resourceToUpdate);
    }
}
