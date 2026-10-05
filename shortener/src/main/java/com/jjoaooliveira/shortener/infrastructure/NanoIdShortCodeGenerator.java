package com.jjoaooliveira.shortener.infrastructure;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.jjoaooliveira.shortener.url.ShortCodeGenerator;

@Component  
class NanoIdShortCodeGenerator implements ShortCodeGenerator {
    @Override
    public String generate() {
        return NanoIdUtils.randomNanoId(new SecureRandom(), NanoIdUtils.DEFAULT_ALPHABET, 8);
    }
}
