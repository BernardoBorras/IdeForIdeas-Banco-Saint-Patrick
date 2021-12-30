package com.example.demo.controller;

import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    // CREAR USUARIO
    @CrossOrigin
    @PostMapping(path = "/usuario")
    public void postUsuario(@RequestBody UsuarioModel usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getContrasenia());
        usuario.setContrasenia(hash);

        usuarioService.postUsuario(usuario);
    }

    // SISTEMA DE LOGUEO
    // Revisa las credenciales. Si son validas crea un token y se lo da al usuario.
    @CrossOrigin
    @PostMapping(path = "/login")
    public String login(@RequestBody UsuarioModel usuario) {

        UsuarioModel usuarioLogueado = usuarioService.getUsuarioByCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

    // VALIDA EL TOKEN
    // Si el toquen es valido, prosigue con la request, si es falso devuelve -1
    private Long validarToken(String token) {
        try {
            return Long.parseLong(jwtUtil.getKey(token));
        } catch (Exception e) {
            return (long) -1;
        }
    }

    // BUSCAR USUARIO
    @CrossOrigin
    @GetMapping(path = "/usuario")
    public Optional<UsuarioModel> getUsuario(@RequestHeader(value = "Authorization") String token) {
        Long usuarioId = validarToken(token);
        if (usuarioId > 0) {
            return usuarioService.getUsuario(usuarioId);
        } else {
            return null;
        }
    }

    // ELIMINAR USUARIO
    @CrossOrigin
    @DeleteMapping(path = "/usuario")
    public void deleteUsuario(@RequestHeader(value = "Authorization") String token) {
        Long usuarioId = validarToken(token);
        if (usuarioId > 0) {
            usuarioService.deleteUsuario(usuarioId);
        } 
    }
}
