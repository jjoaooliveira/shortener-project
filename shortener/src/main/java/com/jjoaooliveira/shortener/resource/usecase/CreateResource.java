package com.jjoaooliveira.shortener.resource.usecase;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;

public class CreateResource extends ResourceUseCase {

    public CreateResource(Resources resources) {
        super(resources);
    }

    ResourceDTO create(ResourceRequest request) {
        Resource newResource = parseRequest(request);
        Resource savedResource = resources.save(newResource);
        ResourceDTO response = ResourceDTO.fromResource(savedResource);
        
        return response;
    }
}
