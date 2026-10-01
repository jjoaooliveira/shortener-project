package com.jjoaooliveira.shortener.resource;

import java.time.Instant;

public class Resource {
    private final Code code;
    private String url;
    private Instant createdAt;

    public Resource(Code code, String url, Instant createdAt) {
        this.code = code;
        this.url = url;
        this.createdAt = createdAt;
    }

    public String getCode() {
        return this.code.code();
    }

    public String getUrl() {
        return this.url;
    }

    public Instant getCreationDate() {
        return createdAt;
    }
}
