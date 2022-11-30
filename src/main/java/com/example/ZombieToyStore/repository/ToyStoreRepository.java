package com.example.ZombieToyStore.repository;

import com.example.ZombieToyStore.model.ToyStoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyStoreRepository extends JpaRepository<ToyStoreModel, Long> {
}
