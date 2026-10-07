package com.jjoaooliveira.shortener.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjoaooliveira.shortener.url.Url;
import com.jjoaooliveira.shortener.url.Urls;

interface UrlRepository extends Urls, JpaRepository<Url, Long> {

}
