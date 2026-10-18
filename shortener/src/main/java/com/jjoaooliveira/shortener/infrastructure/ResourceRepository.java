package com.jjoaooliveira.shortener.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjoaooliveira.shortener.resource.Resource;
import com.jjoaooliveira.shortener.resource.Resources;

interface ResourceRepository extends Resources, JpaRepository<Resource, Long> {

}
