package com.jjoaooliveira.shortener.resource;

public interface Resources {
    Resource save(Resource url);
    Resource findUrlByCode(String code);
    boolean existsByCode(String code);
}
