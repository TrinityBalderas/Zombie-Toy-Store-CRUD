package com.example.ZombieToyStore.service;

import com.example.ZombieToyStore.exception.ProductNotFoundException;
import com.example.ZombieToyStore.model.ToyStoreModel;
import com.example.ZombieToyStore.repository.ToyStoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToyStoreService {
    private ToyStoreRepository toyStoreRepository;

    public ToyStoreService(ToyStoreRepository toyStoreRepository){
        this.toyStoreRepository = toyStoreRepository;
    }

    public List<ToyStoreModel> getAllProducts(){
        return toyStoreRepository.findAll();
    }

    public ToyStoreModel getProductById(Long id){
        Optional<ToyStoreModel> searchResult = toyStoreRepository.findById(id);
        if(searchResult.isEmpty()){
            throw new ProductNotFoundException("Product " + id + " not found.");
        }
        return (ToyStoreModel) searchResult.get();
    }

    public ToyStoreModel createProduct(ToyStoreModel body){
        body.setId(null);
        ToyStoreModel saved = toyStoreRepository.save(body);
        return saved;
    }

    public ToyStoreModel updateToyStore(Long id, ToyStoreModel body) {
        body.setId(id);
        ToyStoreModel original = toyStoreRepository.findById(id).get();
        if(body.getName() != null) {
            original.setName(body.getName());
        }
        if(body.getPrice() != null) {
            original.setPrice(body.getPrice());
        }
        if(body.getDescription() != null) {
            original.setDescription(body.getDescription());
        }
        return toyStoreRepository.save(original);
    }

    public void deleteProduct(Long id) {
        if(toyStoreRepository.existsById(id)){
            toyStoreRepository.deleteById(id);
        }
    }
}
