package com.jjoaooliveira.shortener.resource.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.ResourceFactory;
import com.jjoaooliveira.shortener.resource.domain.Resources;

@ExtendWith(MockitoExtension.class)
class ResourceUseCasesTest {
    private static final UUID CUSTOMER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private static final Instant TIMESTAMP = Instant.parse("2026-01-01T10:15:30Z");
    private static final String CODE = "Ab12Cd34";
    private static final String ADDRESS = "https://example.com/resource";

    @Mock
    private Resources resources;

    @Test
    void shouldCreateResourceFromRequestAndReturnSavedResource() {
        // Arrange
        Resource savedResource = ResourceFactory.makeResource(CODE, ADDRESS, TIMESTAMP, CUSTOMER_ID);
        when(resources.save(any(Resource.class))).thenReturn(savedResource);
        CreateResource useCase = new CreateResource(resources);

        // Act
        ResourceDTO result = useCase.create(request(null, CODE, ADDRESS));

        // Assert
        ArgumentCaptor<Resource> resourceCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(resources).save(resourceCaptor.capture());
        assertThat(resourceCaptor.getValue().getCode()).isEqualTo(CODE);
        assertThat(resourceCaptor.getValue().getAddress()).isEqualTo(ADDRESS);
        assertThat(resourceCaptor.getValue().getCreatedAt()).isEqualTo(TIMESTAMP);
        assertThat(resourceCaptor.getValue().getCustomerId()).isEqualTo(CUSTOMER_ID);
        assertThat(result).isEqualTo(new ResourceDTO(null, CODE, ADDRESS, TIMESTAMP));
    }

    @Test
    void shouldDeleteResourceForCustomer() {
        // Arrange
        when(resources.deleteByIdAndCustomerId(42L, CUSTOMER_ID)).thenReturn(1);
        DeleteResource useCase = new DeleteResource(resources);

        // Act
        useCase.delete(request(42L, CODE, ADDRESS));

        // Assert
        verify(resources).deleteByIdAndCustomerId(42L, CUSTOMER_ID);
    }

    @Test
    void shouldThrowWhenResourceCannotBeDeleted() {
        // Arrange
        when(resources.deleteByIdAndCustomerId(42L, CUSTOMER_ID)).thenReturn(0);
        DeleteResource useCase = new DeleteResource(resources);

        // Act
        assertThrows(ResourceNotFoundException.class, () -> useCase.delete(request(42L, CODE, ADDRESS)));

        // Assert
        verify(resources).deleteByIdAndCustomerId(42L, CUSTOMER_ID);
    }

    @Test
    void shouldReturnAllResourcesForCustomerAsDtos() {
        // Arrange
        List<Resource> customerResources = List.of(
            ResourceFactory.makeResource(CODE, ADDRESS, TIMESTAMP, CUSTOMER_ID),
            ResourceFactory.makeResource("Cd34Ef56", "https://example.com/other", TIMESTAMP, CUSTOMER_ID)
        );
        when(resources.findByCustomerId(CUSTOMER_ID)).thenReturn(customerResources);
        GetAllResources useCase = new GetAllResources(resources);

        // Act
        List<ResourceDTO> result = useCase.get(request(null, CODE, ADDRESS));

        // Assert
        verify(resources).findByCustomerId(CUSTOMER_ID);
        assertThat(result).containsExactly(
            new ResourceDTO(null, CODE, ADDRESS, TIMESTAMP),
            new ResourceDTO(null, "Cd34Ef56", "https://example.com/other", TIMESTAMP)
        );
    }

    @Test
    void shouldUpdateResourceAddress() {
        // Arrange
        Resource resource = ResourceFactory.makeResource(CODE, ADDRESS, TIMESTAMP, CUSTOMER_ID);
        when(resources.findByCodeAndCustomerId(CODE, CUSTOMER_ID)).thenReturn(Optional.of(resource));
        UpdateResourceAddress useCase = new UpdateResourceAddress(resources);
        String updatedAddress = "https://example.com/updated";

        // Act
        ResourceDTO result = useCase.update(request(null, CODE, updatedAddress));

        // Assert
        verify(resources).findByCodeAndCustomerId(CODE, CUSTOMER_ID);
        assertThat(result).isEqualTo(new ResourceDTO(null, CODE, updatedAddress, TIMESTAMP));
    }

    @Test
    void shouldThrowWhenResourceToUpdateDoesNotExist() {
        // Arrange
        when(resources.findByCodeAndCustomerId(CODE, CUSTOMER_ID)).thenReturn(Optional.empty());
        UpdateResourceAddress useCase = new UpdateResourceAddress(resources);

        // Act
        assertThrows(RuntimeException.class, () -> useCase.update(request(null, CODE, ADDRESS)));

        // Assert
        verify(resources).findByCodeAndCustomerId(CODE, CUSTOMER_ID);
    }

    private static ResourceRequest request(Long resourceId, String code, String address) {
        return new ResourceRequest(
            new ResourceDTO(resourceId, code, address, TIMESTAMP),
            CUSTOMER_ID,
            TIMESTAMP
        );
    }
}
