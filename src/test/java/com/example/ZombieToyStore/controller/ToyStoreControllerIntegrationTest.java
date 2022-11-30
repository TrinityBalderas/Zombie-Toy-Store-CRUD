package com.example.ZombieToyStore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class ToyStoreControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ToyStoreController mockToyStoreController;

    @Test
    public void getProduct_ReturnProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/toystore"))
                .andReturn()
                .getResponse();

        verify(mockToyStoreController).getAllProducts();
    }
}
