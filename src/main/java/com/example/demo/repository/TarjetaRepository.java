package com.example.demo.repository;

import java.util.ArrayList;

import com.example.demo.models.TarjetaModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends CrudRepository<TarjetaModel, Long> {

    public abstract ArrayList<TarjetaModel> findByNumero(String numero);

}