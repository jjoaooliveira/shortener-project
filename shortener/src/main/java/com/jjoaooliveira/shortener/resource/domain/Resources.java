package com.jjoaooliveira.shortener.resource.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Resources extends JpaRepository<Resource, Long> {
    Optional<Resource> findByIdAndUsername(Long id, String username);
    Page<Resource> findByUsername(String username, Pageable pageable);
    int deleteByIdAndUsername(Long id, String username);
}
