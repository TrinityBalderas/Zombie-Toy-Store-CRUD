package com.example.ZombieToyStore.controller;

import com.example.ZombieToyStore.exception.ProductNotFoundException;
import com.example.ZombieToyStore.model.ToyStoreModel;
import com.example.ZombieToyStore.responses.ToyStoreResponse;
import com.example.ZombieToyStore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("/localhost:4200")
@RequestMapping("toystore")
public class ToyStoreController {

    private ToyStoreService toyStoreService;

    @Autowired
    public ToyStoreController(ToyStoreService toyStoreService) {
        this.toyStoreService = toyStoreService;
    }

    @GetMapping
    public List<ToyStoreModel> getAllProducts() {
        return toyStoreService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<ToyStoreModel> getProductById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(toyStoreService.getProductById(id));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity(new ToyStoreResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ToyStoreModel createProduct(@RequestBody ToyStoreModel body) {
       return toyStoreService.createProduct(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<ToyStoreModel> updateProduct(@PathVariable Long id, @RequestBody ToyStoreModel body) {
        try {
            return ResponseEntity.ok(toyStoreService.updateToyStore(id, body));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity(new ToyStoreResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ToyStoreResponse> delete(@PathVariable Long id){
        toyStoreService.deleteProduct(id);
        return ResponseEntity.ok(new ToyStoreResponse(null, "Product " + id + " was deleted."));
    }
}
