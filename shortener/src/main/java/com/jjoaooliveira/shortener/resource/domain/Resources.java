package com.jjoaooliveira.shortener.resource.domain;

public interface Resources {
    Resource save(Resource resource);
    Resource findUrlByCode(String code);
    boolean existsByCode(String code);
}
