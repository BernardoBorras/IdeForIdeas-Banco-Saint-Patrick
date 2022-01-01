package com.example.demo.controller;

import com.example.demo.models.TarjetaModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.service.TarjetaService;
import com.example.demo.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private JWTUtil jwtUtil;

    // CREAR TARJETA (Solo accesible desde PostMan o similares)
    @CrossOrigin
    @PostMapping(path = "/usuario")
    public void postTarjeta(@RequestBody TarjetaModel tarjeta) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, tarjeta.getPin());
        tarjeta.setPin(hash);

        tarjetaService.postTarjeta(tarjeta);
    }

    // SISTEMA DE LOGUEO
    // Revisa las credenciales. Si son validas crea un token y se lo da al usuario.
    @CrossOrigin
    @PostMapping(path = "/login")
    public String login(@RequestBody TarjetaModel tarjeta) {
        TarjetaModel tarjetaLogueada = tarjetaService.getTarjetaByCredenciales(tarjeta);
        if (tarjetaLogueada != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(tarjetaLogueada.getId()), tarjetaLogueada.getNumero());
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

    // GET USUARIO
    @CrossOrigin
    @GetMapping(path = "/usuario")
    public UsuarioModel getUsuario(@RequestHeader(value = "Authorization") String token) {
        Long tarjetaId = validarToken(token);
        if (tarjetaId > 0) {
            return tarjetaService.getUsuario(tarjetaId);
        } else {
            return null;
        }
    }
}