package com.jjoaooliveira.shortener.resource.usecase.user;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

public class GetAllResourcesByUsername extends ResourceUseCase {

    public GetAllResourcesByUsername(Resources resources) {
        super(resources);
    }
    
    @PreAuthorize("hasAuthority('resource:read')")
    public List<ResourceDTO> get(String username) {
        List<Resource> resourceList = resources.findByUsername(username);
        
        List<ResourceDTO> resourceDTOs = resourceList.stream()
            .map(ResourceDTO::fromResource)
            .toList();
        
        return resourceDTOs;
    }
}
