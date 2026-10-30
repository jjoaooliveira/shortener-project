package com.jjoaooliveira.shortener.customer.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class CustomerTest {
    private final Name name = new Name("Test Name");
    private final Email email = new Email("test@email.com");

    @Test
    void shouldCreateCustomerWithNameAndEmail() {
        // Arrange & Act
        Customer customer = new Customer(name, email);

        // Assert
        assertThat(customer.getName()).isEqualTo("Test Name");
        assertThat(customer.getEmail()).isEqualTo("test@email.com");
    }

    @Test
    void shouldCreateCustomerWithIdNameAndEmail() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        Customer customer = new Customer(id, name, email);

        // Assert
        assertThat(customer.getName()).isEqualTo("Test Name");
        assertThat(customer.getEmail()).isEqualTo("test@email.com");
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> new Customer(null, email)
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> new Customer(name, null)
        );
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> new Customer(null, name, email)
        );
    }

    @Test
    void shouldThrowExceptionWhenNameAndEmailAreNull() {
        assertThrows(
            NullPointerException.class,
            () -> new Customer(null, null)
        );
    }
}
