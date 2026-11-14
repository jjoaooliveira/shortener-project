package com.jjoaooliveira.shortener.resource.usecase.user;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

import jakarta.transaction.Transactional;

public class UpdateResourceAddress extends ResourceUseCase {

    public UpdateResourceAddress(Resources resources) {
        super(resources);
    }

    @PreAuthorize("hasAuthority('resource:update')")
    @Transactional
    ResourceDTO update(UpdateResourceRequest request, String username) {
        String code = request.code();
        
        Resource resourceToUpdate = resources
            .findByCodeAndUsername(code, username)
            .orElseThrow(RuntimeException::new);
        
        resourceToUpdate.updateAddress(request.address());

        return ResourceDTO.fromResource(resourceToUpdate);
    }
}
