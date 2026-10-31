package com.jjoaooliveira.shortener.resource.usecase;

import java.util.UUID;

import com.jjoaooliveira.shortener.resource.domain.Resources;

import jakarta.transaction.Transactional;

public class DeleteResource extends ResourceUseCase {

    public DeleteResource(Resources resources) {
        super(resources);
    }
    
    @Transactional
    void delete(ResourceRequest request) {
        Long resourceId = request.resource().id();
        UUID customerId = request.customerId();
        int deletedRows = resources.deleteByIdAndCustomerId(resourceId, customerId);

        if (deletedRows == 0) {
            throw new ResourceNotFoundException();
        }
    }
}
