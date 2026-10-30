package com.jjoaooliveira.shortener.resource.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Resources {
    Resource save(Resource resource);
    Optional<Resource> findByCode(String code);
    boolean existsByCode(String code);
    List<Resource> findByCustomerId(UUID customerId);
}
