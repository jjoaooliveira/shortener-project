package com.jjoaooliveira.shortener.resource.usecase.user;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceNotFoundException;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

import jakarta.transaction.Transactional;

public class UpdateResourceById extends ResourceUseCase {

    public UpdateResourceById(Resources resources) {
        super(resources);
    }

    @PreAuthorize("hasAuthority('resource:update')")
    @Transactional
    public ResourceDTO update(final Long id, final UpdateResourceRequest request, final String username) {
        Resource resourceToUpdate = resources
            .findByIdAndUsername(id, username)
            .orElseThrow(ResourceNotFoundException::new);
        
        resourceToUpdate.updateAddress(request.address());

        return ResourceDTO.fromResource(resourceToUpdate);
    }
}
