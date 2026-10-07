package com.jjoaooliveira.shortener.url;

public interface Urls {
    Url save(Url url);
    Url findUrlByCode(String code);
    boolean existsByCode(String code);
}
