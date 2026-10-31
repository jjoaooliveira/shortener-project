package com.jjoaooliveira.shortener.resource.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Resources {
    Resource save(Resource resource);
    Optional<Resource> findByCodeAndCustomerId(String code, UUID customerId);
    List<Resource> findByCustomerId(UUID customerId);
    int deleteByIdAndCustomerId(Long id, UUID customerId);
}
