package com.jjoaooliveira.shortener.resource.usecase;

import java.util.List;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;

public class GetAllResources extends ResourceUseCase {

    public GetAllResources(Resources resources) {
        super(resources);
    }
    
    List<ResourceDTO> get(ResourceRequest request) {
        List<Resource> resourceList = resources.findByCustomerId(request.customerId());
        List<ResourceDTO> resourceDTOs = resourceList.stream()
            .map(ResourceDTO::fromResource)
            .toList();
        
        return resourceDTOs;
    }
}
