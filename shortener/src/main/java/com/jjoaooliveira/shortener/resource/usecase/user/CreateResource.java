package com.jjoaooliveira.shortener.resource.usecase.user;

import java.time.Instant;

import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.ResourceFactory;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;
import com.jjoaooliveira.shortener.resource.usecase.ShortCodeGenerator;

public class CreateResource extends ResourceUseCase {
    private final ShortCodeGenerator shortCodeGenerator;

    public CreateResource(Resources resources, ShortCodeGenerator shortCodeGenerator) {
        super(resources);
        this.shortCodeGenerator = shortCodeGenerator;
    }

    @PreAuthorize("hasAuthority('resource:create')")
    public ResourceDTO create(CreateResourceRequest request, String username) {
        Instant createdAt = Instant.now();
        String code = shortCodeGenerator.generate();
        String address = request.address();
        Resource newResource = ResourceFactory.makeResource(code, address, createdAt, username);
        Resource savedResource = resources.save(newResource);
        ResourceDTO response = ResourceDTO.fromResource(savedResource);
        
        return response;
    }
}
