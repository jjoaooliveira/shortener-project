package com.jjoaooliveira.shortener.url;

import org.springframework.stereotype.Service;

@Service 
public class GenerateShortCode {
    private ShortCodeGenerator shortCodeGenerator;

    public GenerateShortCode(ShortCodeGenerator shortCodeGenerator) {
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public String generateShortCode() {
        return shortCodeGenerator.generate();
    }
}
