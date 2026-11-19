package com.jjoaooliveira.shortener.resource.usecase.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;

import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceNotFoundException;
import com.jjoaooliveira.shortener.resource.usecase.ResourceUseCase;

import jakarta.transaction.Transactional;

public class DeleteResourceById extends ResourceUseCase {

    public DeleteResourceById(Resources resources) {
        super(resources);
    }
    
    @PreAuthorize("hasAuthority('resource:delete')")
    @CacheEvict(value = "resources", allEntries = true)
    @Transactional
    public void delete(final DeleteResourceRequest request, final String username) {
        int deletedRows = resources.deleteByIdAndUsername(request.id(), username);

        if (deletedRows == 0) {
            throw new ResourceNotFoundException();
        }
    }
}
