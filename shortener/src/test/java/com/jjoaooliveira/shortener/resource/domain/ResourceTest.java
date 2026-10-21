package com.jjoaooliveira.shortener.resource.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceTest {
    private static final Long ID = 123L;
    private static final Code CODE = new Code("Ab12Cd34");
    private static final Address ADDRESS = new Address("https://www.example.com/a/b?foo=bar&baz=123#section");
    private static final Instant CREATED_AT = Instant.parse("2026-01-01T10:15:30Z");

    @Test
    void shouldCreateResourceThroughFactory() {
        String code = "Ab12Cd34";
        String address = "https://www.example.com/a/b?foo=bar&baz=123#section";
        Instant instant = Instant.now();

        Resource resource = ResourceFactory.makeResource(
                code,
                address,
                instant
        );

        assertThat(resource.getId()).isNull();
        assertThat(resource.getCode()).isEqualTo(code);
        assertThat(resource.getAddress()).isEqualTo(address);
        assertThat(resource.getCreatedAt()).isEqualTo(instant);
    }

    @Test
    void shouldCreateResourceWithoutId() {
        Resource resource = new Resource(
                CODE,
                ADDRESS,
                CREATED_AT
        );

        assertThat(resource.getId()).isNull();
        assertThat(resource.getCode()).isEqualTo(CODE.value());
        assertThat(resource.getAddress()).isEqualTo(ADDRESS.value());
        assertThat(resource.getCreatedAt()).isEqualTo(CREATED_AT);
    }

    @Test
    void shouldCreateResourceWithAllFields() {
        Resource url = new Resource(
                ID,
                CODE,
                ADDRESS,
                CREATED_AT
        );

        assertThat(url.getId()).isNotNull();
        assertThat(url.getCode()).isEqualTo(CODE.value());
        assertThat(url.getAddress()).isEqualTo(ADDRESS.value());
        assertThat(url.getCreatedAt()).isEqualTo(CREATED_AT);
    }

    @Test
    void shouldAcceptNullId() {
        Resource url = new Resource(
                null,
                CODE,
                ADDRESS,
                CREATED_AT
        );

        assertThat(url.getId()).isNull();
    }

    @Test
    void shouldFailOnEmptyCode() {
        String address = ADDRESS.value();
        String emptyCode = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ResourceFactory.makeResource(emptyCode, address, CREATED_AT);
        });

        assertThat(exception.getMessage()).isEqualTo("Code value is null or blank");
    }
    
    @Test
    void shouldFailOnEmptyAddress() {
        String emptyAddress = "";
        String code = CODE.value();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ResourceFactory.makeResource(code, emptyAddress, CREATED_AT);
        });

        assertThat(exception.getMessage()).isEqualTo("Address value is null or blank");
    }

    @Test
    void shouldFailWhenCreatedAtIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                CODE, 
                ADDRESS, 
                null
            );
        });

        assertThat(exception.getMessage()).isEqualTo("Resource createdAt is null");
    }

    @Test
    void shouldFailWhenAddressIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                CODE,
                null,
                CREATED_AT
            );
        });

        assertThat(exception.getMessage()).isEqualTo("Resource address is null");
    }

    @Test
    void shouldFailWhenCodeIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                null,
                ADDRESS,
                CREATED_AT
            );
        });

        assertThat(exception.getMessage()).isEqualTo("Resource code is null");
    }
}