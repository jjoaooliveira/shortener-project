package com.jjoaooliveira.shortener.resource.domain;

import java.util.List;
import java.util.Optional;

public interface Resources {
    Resource save(Resource resource);
    Optional<Resource> findByCodeAndCustomerId(String code, String customerId);
    List<Resource> findByCustomerId(String customerId);
    int deleteByIdAndCustomerId(Long id, String customerId);
}
