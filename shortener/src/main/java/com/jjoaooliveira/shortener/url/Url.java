package com.jjoaooliveira.shortener.url;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private Code code;

    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public Url() {}

    Url(String code, String originalUrl, Instant createdAt) {
        this.code = new Code(code);
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }

    public Url(Long id, String code, String originalUrl, Instant createdAt) {
        this.id = id;
        this.code = new Code(code);
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }

    public Long getLong() {
        return id;
    }

    public String getCode() {
        return code.code();
    }
    
    public String getOriginalUrl() {
        return originalUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
