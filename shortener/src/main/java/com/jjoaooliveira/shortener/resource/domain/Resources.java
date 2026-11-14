package com.jjoaooliveira.shortener.resource.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Resources extends JpaRepository<Resource, Long> {
    Optional<Resource> findByCodeAndUsername(String code, String username);
    List<Resource> findByUsername(String username);
    int deleteByIdAndUsername(Long id, String username);
}
