package com.jjoaooliveira.shortener.resource.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceTest {
    private static final Long ID = 123L;
    private static final Code CODE = new Code("Ab12Cd34");
    private static final Address ADDRESS = new Address("https://www.example.com/a/b?foo=bar&baz=123#section");
    private static final Instant CREATED_AT = Instant.parse("2026-01-01T10:15:30Z");
    private static final UUID CUSTOMER_ID = UUID.randomUUID();

    @Test
    void shouldCreateResourceThroughFactory() {
        // Arrange
        String code = "Ab12Cd34";
        String address = "https://www.example.com/a/b?foo=bar&baz=123#section";
        Instant instant = Instant.now();

        // Act
        Resource resource = ResourceFactory.makeResource(
                code,
                address,
                instant,
                CUSTOMER_ID
        );

        // Assert
        assertThat(resource.getId()).isNull();
        assertThat(resource.getCode()).isEqualTo(code);
        assertThat(resource.getAddress()).isEqualTo(address);
        assertThat(resource.getCreatedAt()).isEqualTo(instant);
    }

    @Test
    void shouldCreateResourceWithoutId() {
        // Arrange
        // Act
        Resource resource = new Resource(
                CODE,
                ADDRESS,
                CREATED_AT,
                CUSTOMER_ID
        );

        // Assert
        assertThat(resource.getId()).isNull();
        assertThat(resource.getCode()).isEqualTo(CODE.value());
        assertThat(resource.getAddress()).isEqualTo(ADDRESS.value());
        assertThat(resource.getCreatedAt()).isEqualTo(CREATED_AT);
    }

    @Test
    void shouldCreateResourceWithAllFields() {
        // Arrange
        // Act
        Resource resource = new Resource(
                ID,
                CODE,
                ADDRESS,
                CREATED_AT,
                CUSTOMER_ID
        );

        // Assert
        assertThat(resource.getId()).isNotNull();
        assertThat(resource.getCode()).isEqualTo(CODE.value());
        assertThat(resource.getAddress()).isEqualTo(ADDRESS.value());
        assertThat(resource.getCreatedAt()).isEqualTo(CREATED_AT);
    }

    @Test
    void shouldAcceptNullId() {
        // Arrange
        // Act
        Resource resource = new Resource(
                null,
                CODE,
                ADDRESS,
                CREATED_AT,
                CUSTOMER_ID
        );

        // Assert
        assertThat(resource.getId()).isNull();
    }

    @Test
    void shouldFailOnEmptyCode() {
        // Arrange
        String address = ADDRESS.value();
        String emptyCode = "";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ResourceFactory.makeResource(emptyCode, address, CREATED_AT, CUSTOMER_ID);
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Code value is null or blank");
    }
    
    @Test
    void shouldFailOnEmptyAddress() {
        // Arrange
        String emptyAddress = "";
        String code = CODE.value();

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ResourceFactory.makeResource(code, emptyAddress, CREATED_AT, CUSTOMER_ID);
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Address value is null or blank");
    }

    @Test
    void shouldFailWhenCreatedAtIsNull() {
        // Arrange
        Instant createdAt = null;

        // Act
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                CODE, 
                ADDRESS, 
                createdAt,
                CUSTOMER_ID
            );
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Resource createdAt is null");
    }

    @Test
    void shouldFailWhenAddressIsNull() {
        // Arrange
        Address address = null;

        // Act
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                CODE,
                address,
                CREATED_AT,
                CUSTOMER_ID
            );
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Resource address is null");
    }

    @Test
    void shouldFailWhenCodeIsNull() {
        // Arrange
        Code code = null;

        // Act
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                code,
                ADDRESS,
                CREATED_AT,
                CUSTOMER_ID
            );
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Resource code is null");
    }

    @Test
    void shouldFailWhenCustomerIdIsNull() {
        // Arrange
        UUID customerId = null;

        // Act
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Resource(
                CODE,
                ADDRESS,
                CREATED_AT,
                customerId
            );
        });

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Resource customerId is null");
    }
}