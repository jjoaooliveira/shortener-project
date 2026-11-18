package com.jjoaooliveira.shortener.resource.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.user.CreateResource;
import com.jjoaooliveira.shortener.resource.usecase.user.CreateResourceRequest;
import com.jjoaooliveira.shortener.resource.usecase.user.DeleteResourceById;
import com.jjoaooliveira.shortener.resource.usecase.user.DeleteResourceRequest;
import com.jjoaooliveira.shortener.resource.usecase.user.GetAllResourcesByUsername;
import com.jjoaooliveira.shortener.resource.usecase.user.GetResourceById;
import com.jjoaooliveira.shortener.resource.usecase.user.GetResourceByIdRequest;
import com.jjoaooliveira.shortener.resource.usecase.user.UpdateResourceById;
import com.jjoaooliveira.shortener.resource.usecase.user.UpdateResourceRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/resources")
class ResourceRestController {
    private ResourceModelAssembler resourceModelAssembler;
    private PagedResourcesAssembler<ResourceDTO> pagedResourcesAssembler;
    private CreateResource createResource;
    private GetResourceById getResourceById;
    private GetAllResourcesByUsername getAllResourcesByUsername;
    private UpdateResourceById updateResourceAddress;
    private DeleteResourceById deleteResourceById;

    ResourceRestController(
        ResourceModelAssembler resourceModelAssembler,
        PagedResourcesAssembler<ResourceDTO> pagedResourcesAssembler,
        CreateResource createResource,
        GetResourceById getResourceById,
        GetAllResourcesByUsername getAllResourcesByUsername,
        UpdateResourceById updateResourceAddress,
        DeleteResourceById deleteResourceById
    ) {
        this.resourceModelAssembler = resourceModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createResource = createResource;
        this.getResourceById = getResourceById;
        this.getAllResourcesByUsername = getAllResourcesByUsername;
        this.updateResourceAddress = updateResourceAddress;
        this.deleteResourceById = deleteResourceById;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public PagedModel<EntityModel<ResourceDTO>> getAllResources( 
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable,
        Authentication authentication
    ) {
        var response = getAllResourcesByUsername.get(authentication.getName(), pageable);
        return pagedResourcesAssembler.toModel(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public EntityModel<ResourceDTO> getResourceById(
        @PathVariable Long id, 
        Authentication authentication
    ) {
        var request = new GetResourceByIdRequest(id);
        var response = getResourceById.get(request, authentication.getName());

        return resourceModelAssembler.toModel(response);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public EntityModel<ResourceDTO> createResource(
        @RequestBody CreateResourceRequest request, 
        Authentication authentication
    ) {
        var response = createResource.create(request, authentication.getName());
        return resourceModelAssembler.toModel(response);
    }
    

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public EntityModel<ResourceDTO> updateResource(
        @PathVariable Long id,
        @RequestBody UpdateResourceRequest request, 
        Authentication authentication
    ) {   
        var response = updateResourceAddress.update(id, request, authentication.getName());
        return resourceModelAssembler.toModel(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteResource(
        @PathVariable Long id, 
        Authentication authentication
    ) {
        var usecaseRequest = new DeleteResourceRequest(id);
        deleteResourceById.delete(usecaseRequest, authentication.getName());
        return ResponseEntity.ok().body("resource deleted succesfully");
    }
}
