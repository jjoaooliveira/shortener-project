package com.jjoaooliveira.shortener.resource.usecase.user;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceNotFoundException;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

public class GetResourceById extends ResourceUseCase {

    public GetResourceById(Resources resources) {
        super(resources);
    }

    @PreAuthorize("hasAuthority('resource:read')")
    public ResourceDTO get(final GetResourceByIdRequest request, final String username) {
        final Long resourceId = request.id();

        final Resource resource = resources
            .findByIdAndUsername(resourceId, username)
            .orElseThrow(ResourceNotFoundException::new);
        
        return ResourceDTO.fromResource(resource);
    }
}
