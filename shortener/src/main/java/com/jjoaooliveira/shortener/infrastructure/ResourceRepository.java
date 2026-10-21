package com.jjoaooliveira.shortener.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.Resources;

interface ResourceRepository extends Resources, JpaRepository<Resource, Long> {

}
