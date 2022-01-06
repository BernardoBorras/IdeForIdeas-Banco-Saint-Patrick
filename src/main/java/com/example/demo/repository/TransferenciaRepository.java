package com.example.demo.repository;

import com.example.demo.models.TransferenciaModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends CrudRepository<TransferenciaModel, Long> {
    
}
