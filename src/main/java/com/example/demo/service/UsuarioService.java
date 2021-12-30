package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void postUsuario(UsuarioModel usuario) {
        usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> getUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Busca por mail al usuario
    // Si lo encuentra verifica la contraseña ingresada
    // Si la contraseña es valida, devuelve la información del usuario
    public UsuarioModel getUsuarioByCredenciales(UsuarioModel usuario) {

        ArrayList<UsuarioModel> lista = usuarioRepository.findByEmail(usuario.getEmail());

        if (!lista.isEmpty()) {

            String contraseniaEncriptada = lista.get(0).getContrasenia();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            if (argon2.verify(contraseniaEncriptada, usuario.getContrasenia())) {
                return lista.get(0);
            }
        }
        return null;
    }
}
