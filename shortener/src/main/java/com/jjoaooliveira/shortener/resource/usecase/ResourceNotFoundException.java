package com.jjoaooliveira.shortener.resource.usecase;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource could not be found");
    }
}
