package com.jjoaooliveira.shortener.resource.usecase.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jjoaooliveira.shortener.resource.domain.Resource;
import com.jjoaooliveira.shortener.resource.domain.ResourceFactory;
import com.jjoaooliveira.shortener.resource.domain.Resources;
import com.jjoaooliveira.shortener.resource.usecase.ResourceDTO;
import com.jjoaooliveira.shortener.resource.usecase.ResourceNotFoundException;
import com.jjoaooliveira.shortener.resource.usecase.ShortCodeGenerator;

@ExtendWith(MockitoExtension.class)
class ResourceUseCasesTest {
    private static final String USERNAME = "customer-123";
    private static final Instant TIMESTAMP = Instant.parse("2026-01-01T10:15:30Z");
    private static final String CODE = "Ab12Cd34";
    private static final String ADDRESS = "https://example.com/resource";

    @Mock
    private Resources resources;

    @Mock
    private ShortCodeGenerator shortCodeGenerator;

    @Test
    void shouldCreateResourceFromRequestAndReturnSavedResource() {
        // Arrange
        Resource savedResource = ResourceFactory.makeResource(CODE, ADDRESS, TIMESTAMP, USERNAME);
        when(resources.save(any(Resource.class))).thenReturn(savedResource);
        when(shortCodeGenerator.generate()).thenReturn(CODE);
        CreateResource useCase = new CreateResource(resources, shortCodeGenerator);

        // Act
        ResourceDTO result = useCase.create(new CreateResourceRequest(ADDRESS), USERNAME);

        // Assert
        ArgumentCaptor<Resource> resourceCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(resources).save(resourceCaptor.capture());
        assertThat(resourceCaptor.getValue().getCode()).isEqualTo(CODE);
        assertThat(resourceCaptor.getValue().getAddress()).isEqualTo(ADDRESS);
        assertThat(resourceCaptor.getValue().getCreatedAt()).isNotNull();
        assertThat(resourceCaptor.getValue().getUsername()).isEqualTo(USERNAME);
        assertThat(result).isEqualTo(new ResourceDTO(null, CODE, ADDRESS, TIMESTAMP));
    }

    @Test
    void shouldDeleteResourceForCustomer() {
        // Arrange
        when(resources.deleteByIdAndUsername(42L, USERNAME)).thenReturn(1);
        DeleteResource useCase = new DeleteResource(resources);

        // Act
        useCase.delete(new DeleteResourceRequest(42L), USERNAME);

        // Assert
        verify(resources).deleteByIdAndUsername(42L, USERNAME);
    }

    @Test
    void shouldThrowWhenResourceCannotBeDeleted() {
        // Arrange
        when(resources.deleteByIdAndUsername(42L, USERNAME)).thenReturn(0);
        DeleteResource useCase = new DeleteResource(resources);

        // Act
        assertThrows(
            ResourceNotFoundException.class,
            () -> useCase.delete(new DeleteResourceRequest(42L), USERNAME)
        );

        // Assert
        verify(resources).deleteByIdAndUsername(42L, USERNAME);
    }

    @Test
    void shouldReturnAllResourcesForCustomerAsDtos() {
        // Arrange
        List<Resource> customerResources = List.of(
            ResourceFactory.makeResource(CODE, ADDRESS, TIMESTAMP, USERNAME),
            ResourceFactory.makeResource("Cd34Ef56", "https://example.com/other", TIMESTAMP, USERNAME)
        );
        when(resources.findByUsername(USERNAME)).thenReturn(customerResources);
        GetAllResourcesByUsername useCase = new GetAllResourcesByUsername(resources);

        // Act
        List<ResourceDTO> result = useCase.get(USERNAME);

        // Assert
        verify(resources).findByUsername(USERNAME);
        assertThat(result).containsExactly(
            new ResourceDTO(null, CODE, ADDRESS, TIMESTAMP),
            new ResourceDTO(null, "Cd34Ef56", "https://example.com/other", TIMESTAMP)
        );
    }

    @Test
    void shouldUpdateResourceAddress() {
        // Arrange
        Resource resource = ResourceFactory.makeResource(CODE, ADDRESS, TIMESTAMP, USERNAME);
        when(resources.findByCodeAndUsername(CODE, USERNAME)).thenReturn(Optional.of(resource));
        UpdateResourceAddress useCase = new UpdateResourceAddress(resources);
        String updatedAddress = "https://example.com/updated";

        // Act
        ResourceDTO result = useCase.update(new UpdateResourceRequest(CODE, updatedAddress), USERNAME);

        // Assert
        verify(resources).findByCodeAndUsername(CODE, USERNAME);
        assertThat(result).isEqualTo(new ResourceDTO(null, CODE, updatedAddress, TIMESTAMP));
    }

    @Test
    void shouldThrowWhenResourceToUpdateDoesNotExist() {
        // Arrange
        when(resources.findByCodeAndUsername(CODE, USERNAME)).thenReturn(Optional.empty());
        UpdateResourceAddress useCase = new UpdateResourceAddress(resources);

        // Act
        assertThrows(
            RuntimeException.class,
            () -> useCase.update(new UpdateResourceRequest(CODE, ADDRESS), USERNAME)
        );

        // Assert
        verify(resources).findByCodeAndUsername(CODE, USERNAME);
    }
}
