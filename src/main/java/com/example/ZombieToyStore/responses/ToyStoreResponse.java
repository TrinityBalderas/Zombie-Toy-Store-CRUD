package com.example.ZombieToyStore.responses;

import com.example.ZombieToyStore.model.ToyStoreModel;

public class ToyStoreResponse {
    public String message;
    public ToyStoreModel body;

    public ToyStoreResponse (ToyStoreModel toyStoreModel, String message){
        this.message = message;
        this.body = toyStoreModel;
    }
}
