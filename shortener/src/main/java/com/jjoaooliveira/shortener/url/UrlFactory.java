package com.jjoaooliveira.shortener.url;

import java.time.Instant;

public class UrlFactory {
    public static Url makeResource(String code, String originalUrl, Instant createdAt) {
        return new Url(code, originalUrl, createdAt);
    }
}
