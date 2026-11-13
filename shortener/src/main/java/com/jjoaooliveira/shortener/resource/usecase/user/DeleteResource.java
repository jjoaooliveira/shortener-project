package com.jjoaooliveira.shortener.resource.usecase.user;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceNotFoundException;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

import jakarta.transaction.Transactional;

public class DeleteResource extends ResourceUseCase {

    public DeleteResource(Resources resources) {
        super(resources);
    }
    
    @PreAuthorize("hasAuthority('resource:delete')")
    @Transactional
    void delete(DeleteResourceRequest request, String customerId) {
        Long resourceId = request.id();
        int deletedRows = resources.deleteByIdAndCustomerId(resourceId, customerId);

        if (deletedRows == 0) {
            throw new ResourceNotFoundException();
        }
    }
}
