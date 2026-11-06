package com.jjoaooliveira.shortener.resource.usecase.user;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

import jakarta.transaction.Transactional;

public class UpdateResourceAddress extends ResourceUseCase {

    public UpdateResourceAddress(Resources resources) {
        super(resources);
    }

    @Transactional
    ResourceDTO update(UpdateResourceRequest request) {
        String code = request.code();
        String customerId = request.customerId();

        Resource resourceToUpdate = resources
            .findByCodeAndCustomerId(code, customerId)
            .orElseThrow(RuntimeException::new);
        
        resourceToUpdate.updateAddress(request.address());

        return ResourceDTO.fromResource(resourceToUpdate);
    }
}
