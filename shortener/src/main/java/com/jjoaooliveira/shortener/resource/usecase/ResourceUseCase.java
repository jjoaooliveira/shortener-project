package com.jjoaooliveira.shortener.resource.usecase;

import com.jjoaooliveira.shortener.resource.domain.Resources;

public abstract class ResourceUseCase {
    protected Resources resources;

    public ResourceUseCase(Resources resources) {
        this.resources = resources;
    }
}
