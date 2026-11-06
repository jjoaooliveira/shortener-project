package com.jjoaooliveira.shortener.resource.usecase.user;

import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceNotFoundException;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

import jakarta.transaction.Transactional;

public class DeleteResource extends ResourceUseCase {

    public DeleteResource(Resources resources) {
        super(resources);
    }
    
    @Transactional
    void delete(DeleteResourceRequest request) {
        Long resourceId = request.id();
        String customerId = request.customerId();
        int deletedRows = resources.deleteByIdAndCustomerId(resourceId, customerId);

        if (deletedRows == 0) {
            throw new ResourceNotFoundException();
        }
    }
}
