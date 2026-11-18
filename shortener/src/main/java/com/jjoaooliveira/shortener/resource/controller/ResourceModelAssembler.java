package com.jjoaooliveira.shortener.resource.controller;

import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;

import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;

public class ResourceModelAssembler implements RepresentationModelAssembler<ResourceDTO, EntityModel<ResourceDTO>>{

    @Override
    public EntityModel<ResourceDTO> toModel(ResourceDTO entity) {
        return EntityModel.of(
            entity,
            linkTo(
                methodOn(ResourceRestController.class)
                .getResourceById(entity.id(), null)
            ).withSelfRel()
            .andAffordance(afford(methodOn(ResourceRestController.class)
                .updateResource(entity.id(), null, null))
            )
        );
    }

}
