package com.jjoaooliveira.shortener.customer.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CustomerTest {
    private static final String USERNAME = "test-user";
    private static final String NAME = "Test Name";
    private static final String EMAIL = "test@email.com";

    @Test
    void shouldCreateCustomerThroughFactory() {
        Customer customer = CustomerFactory.create(USERNAME, EMAIL, NAME);

        assertThat(customer.getUsername()).isEqualTo(USERNAME);
        assertThat(customer.getName()).isEqualTo(NAME);
        assertThat(customer.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void shouldUpdateCustomerName() {
        Customer customer = CustomerFactory.create(USERNAME, EMAIL, NAME);

        customer.updateName("Updated Name");

        assertThat(customer.getName()).isEqualTo("Updated Name");
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> CustomerFactory.create(null, EMAIL, NAME)
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        assertThrows(
            IllegalArgumentException.class,
            () -> CustomerFactory.create(USERNAME, EMAIL, " ")
        );
    }

    @Test
    void shouldThrowExceptionWhenEmailIsBlank() {
        assertThrows(
            IllegalArgumentException.class,
            () -> CustomerFactory.create(USERNAME, " ", NAME)
        );
    }

    @Test
    void shouldThrowExceptionWhenUpdatedNameIsBlank() {
        Customer customer = CustomerFactory.create(USERNAME, EMAIL, NAME);

        assertThrows(IllegalArgumentException.class, () -> customer.updateName(" "));
    }
}
