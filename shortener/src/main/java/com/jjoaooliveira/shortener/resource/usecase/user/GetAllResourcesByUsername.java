package com.jjoaooliveira.shortener.resource.usecase.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

public class GetAllResourcesByUsername extends ResourceUseCase {

    public GetAllResourcesByUsername(Resources resources) {
        super(resources);
    }
    
    @PreAuthorize("hasAuthority('resource:read:list')")
    public Page<ResourceDTO> get(String username, Pageable pageable) {
        return resources
            .findByUsername(username, pageable)
            .map(ResourceDTO::fromResource);
    }
}
