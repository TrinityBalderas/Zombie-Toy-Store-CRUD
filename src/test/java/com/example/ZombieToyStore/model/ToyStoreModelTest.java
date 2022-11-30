package com.example.ZombieToyStore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToyStoreModelTest {
    @Test
    public void id_GetterAndSetter() {
        ToyStoreModel input = new ToyStoreModel();
        input.setId(1L);
        assertEquals(1L, input.getId());
    }
    @Test
    public void productName_GetterAndSetter() {
        ToyStoreModel input = new ToyStoreModel();
        input.setName("toy");
        assertEquals("toy", input.getName());
    }
    @Test
    public void productPrice_GetterAndSetter() {
        ToyStoreModel input = new ToyStoreModel();
        input.setPrice(1.34);
        assertEquals(1.34, input.getPrice());
    }
    @Test
    public void productDescription_GetterAndSetter() {
        ToyStoreModel input = new ToyStoreModel();
        input.setDescription("Its a toy");
        assertEquals("Its a toy", input.getDescription());
    }
    @Test
    public void toStringTest(){
        ToyStoreModel input = new ToyStoreModel();
        String expected = "ToyStoreModel{id=null, productName='null', productPrice=null, productDescription='null'}";
        assertEquals(expected, input.toString());
    }
}
