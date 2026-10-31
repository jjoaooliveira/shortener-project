package com.jjoaooliveira.shortener.resource.usecase;

public class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException() {
        super("Resource could not be found");
    }
}
