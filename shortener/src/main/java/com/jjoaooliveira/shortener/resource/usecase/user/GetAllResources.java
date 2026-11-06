package com.jjoaooliveira.shortener.resource.usecase.user;

import java.util.List;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

public class GetAllResources extends ResourceUseCase {

    public GetAllResources(Resources resources) {
        super(resources);
    }
    
    public List<ResourceDTO> get(GetAllResourcesRequest request) {
        String customerId = request.customerId();
        List<Resource> resourceList = resources.findByCustomerId(customerId);
        List<ResourceDTO> resourceDTOs = resourceList.stream()
            .map(ResourceDTO::fromResource)
            .toList();
        
        return resourceDTOs;
    }
}
