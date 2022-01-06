package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.models.TarjetaModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.TarjetaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
@Transactional
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    // Busca por nº de tarjeta la información completa de dicha tarjeta
    // Si la encuentra, verifica que el pin ingresado sea correcto
    // Si el pin ingresado es correcto, devuelve la información del usuario a quien pertenece
    public TarjetaModel getTarjetaByCredenciales(TarjetaModel tarjeta) {

        ArrayList<TarjetaModel> infoTarjeta = tarjetaRepository.findByNumero(tarjeta.getNumero());

        if (!infoTarjeta.isEmpty()) {

            String pinEncriptado = infoTarjeta.get(0).getPin();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            if (argon2.verify(pinEncriptado, tarjeta.getPin())) {

                return infoTarjeta.get(0);
            }
        }
        return null;
    }

    public void postTarjeta(TarjetaModel tarjeta) {
        tarjetaRepository.save(tarjeta);
    }

    public UsuarioModel getUsuario(Long tarjetaId) {
        TarjetaModel tarjeta = tarjetaRepository.findById(tarjetaId).get();
        return tarjeta.getUsuario();
    }

}
