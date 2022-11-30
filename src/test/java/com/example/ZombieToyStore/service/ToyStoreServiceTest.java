package com.example.ZombieToyStore.service;

import com.example.ZombieToyStore.exception.ProductNotFoundException;
import com.example.ZombieToyStore.model.ToyStoreModel;
import com.example.ZombieToyStore.repository.ToyStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ToyStoreServiceTest {
    private ToyStoreRepository mockToyStoreRepository;
    private ToyStoreModel insideDatabase;
    private ToyStoreService toyStoreService;

    @BeforeEach
    public void setup() {
        insideDatabase = new ToyStoreModel();
        insideDatabase.setId(1L);
        insideDatabase.setName("Cali");
        insideDatabase.setPrice(3.14);
        insideDatabase.setDescription("I am a toy");
        mockToyStoreRepository = mock(ToyStoreRepository.class);
        toyStoreService = new ToyStoreService(mockToyStoreRepository);
        when(mockToyStoreRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        when(mockToyStoreRepository.save(any())).thenAnswer(method -> method.getArgument(0));
    }
    @Test
    public void getAllProducts() {
        toyStoreService.getAllProducts();
        verify(mockToyStoreRepository).findAll();
    }
    @Test
    public void getById_IfSearchResultIsEmptyThenReturnError() {
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            toyStoreService.getProductById(2L);
        });
        assertEquals("Product 2 not found.", exception.getMessage());
    }
    @Test
    public void getProductById_GetByRepo() {
        Mockito.when(mockToyStoreRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        ToyStoreModel actual = toyStoreService.getProductById(1L);
        assertEquals(1L, actual.getId());
    }
    @Test
    public void create_CallsRepo() {
        ToyStoreModel input = new ToyStoreModel();
        toyStoreService.createProduct(input);
        verify(mockToyStoreRepository).save(any());
    }
    @Test
    public void update_ProductNameWhenNotNull() {
        ToyStoreModel input = new ToyStoreModel();
        input.setName("Zoe");
        ToyStoreModel actual = toyStoreService.updateToyStore(1L, input);
        verify(mockToyStoreRepository).save(any());
        assertEquals("Zoe", actual.getName());
    }
    @Test
    public void update_ProductNameReturnOrignialWhenInputIsNull() {
        ToyStoreModel input = new ToyStoreModel();
        input.setName(null);
        ToyStoreModel actual = toyStoreService.updateToyStore(1L, input);
        verify(mockToyStoreRepository).save(any());
        assertEquals("Cali", actual.getName());
    }
    @Test
    public void update_ProductPriceWhenNotNull() {
        ToyStoreModel input = new ToyStoreModel();
        input.setPrice(5.00);
        ToyStoreModel actual = toyStoreService.updateToyStore(1L, input);
        verify(mockToyStoreRepository).save(any());
        assertEquals(5.00, actual.getPrice());
    }
    @Test
    public void update_ProductPriceReturnOrignialWhenInputIsNull() {
        ToyStoreModel input = new ToyStoreModel();
        input.setPrice(null);
        ToyStoreModel actual = toyStoreService.updateToyStore(1L, input);
        verify(mockToyStoreRepository).save(any());
        assertEquals(3.14, actual.getPrice());
    }
    @Test
    public void update_ProductDescriptionWhenNotNull() {
        ToyStoreModel input = new ToyStoreModel();
        input.setDescription("I am a dog");
        ToyStoreModel actual = toyStoreService.updateToyStore(1L, input);
        verify(mockToyStoreRepository).save(any());
        assertEquals("I am a dog", actual.getDescription());
    }
    @Test
    public void update_ProductDescriptionReturnOrignialWhenInputIsNull() {
        ToyStoreModel input = new ToyStoreModel();
        input.setDescription(null);
        ToyStoreModel actual = toyStoreService.updateToyStore(1L, input);
        verify(mockToyStoreRepository).save(any());
        assertEquals("I am a toy", actual.getDescription());
    }
    @Test
    public void delete_IsDeletedByRepo() {
        Mockito.when(mockToyStoreRepository.existsById(1L)).thenReturn(true);
        toyStoreService.deleteProduct(1L);
        verify(mockToyStoreRepository).deleteById(1L);
    }
    @Test
    public void delete_IfInputDoesNotExistsThenDoesNothing() {
        Mockito.when(mockToyStoreRepository.existsById(2L)).thenReturn(false);
        toyStoreService.deleteProduct(2L);
        verify(mockToyStoreRepository, times(0)).deleteById(2L);
    }
}
