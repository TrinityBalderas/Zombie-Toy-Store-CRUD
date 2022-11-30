package com.example.ZombieToyStore.controller;

import com.example.ZombieToyStore.exception.ProductNotFoundException;
import com.example.ZombieToyStore.model.ToyStoreModel;
import com.example.ZombieToyStore.responses.ToyStoreResponse;
import com.example.ZombieToyStore.service.ToyStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ToyStoreControllerTest {
    ToyStoreService mockToyStoreService;
    ToyStoreController toyStoreController;

    @BeforeEach
    public void set() {
        mockToyStoreService = mock(ToyStoreService.class);
        toyStoreController = new ToyStoreController(mockToyStoreService);
    }
    @Test
    public void getAll_CallsService() {
        toyStoreController.getAllProducts();
        verify(mockToyStoreService).getAllProducts();
    }
    @Test
    public void getAll_ReturnValuesFromService() {
        when(mockToyStoreService.getAllProducts()).thenReturn(null);
        assertEquals(null, toyStoreController.getAllProducts());
    }
    @Test
    public void getProductById_ShouldReturn404WhenRecordDoesNotExits() {
        Mockito.when(mockToyStoreService.getProductById(1L)).thenThrow(new ProductNotFoundException("Product 1 not found."));
        ResponseEntity<ToyStoreModel> actual = toyStoreController.getProductById(1L);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void createProduct_ShouldCallService() {
        ToyStoreModel input = new ToyStoreModel();
        toyStoreController.createProduct(input);
        verify(mockToyStoreService).createProduct(input);
    }
    @Test
    public void updateProduct_ShouldReturn404WhenRecordDoesNotExists() {
        ToyStoreModel input = new ToyStoreModel();
        Mockito.when(mockToyStoreService.updateToyStore(1L, input)).thenThrow(new ProductNotFoundException("Product 1 not found."));
        ResponseEntity<ToyStoreModel> actual = toyStoreController.updateProduct(1L, input);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void deleteProduct_CallsService() {
        toyStoreController.delete(1L);
        verify(mockToyStoreService).deleteProduct(1L);
    }
    @Test
    public void delete_TaskCompletedMessage() {
        ToyStoreModel input = new ToyStoreModel();
        ResponseEntity<ToyStoreResponse> actual = toyStoreController.delete(1L);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Product 1 was deleted.", actual.getBody().message);
    }
}
