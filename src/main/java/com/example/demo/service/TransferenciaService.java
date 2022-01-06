package com.example.demo.service;

import java.sql.Date;
import java.util.ArrayList;

import javax.transaction.Transactional;

import com.example.demo.models.TarjetaModel;
import com.example.demo.models.TransferenciaModel;
import com.example.demo.repository.TarjetaRepository;
import com.example.demo.repository.TransferenciaRepository;
import com.example.demo.utils.DatosTransferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    public TransferenciaModel transferir(DatosTransferencia datosTransf, Long tarjetaId) {

        System.out.println("Iniciando transferencia");

        System.out.println("Buscando información completa de las tarjetas: " + datosTransf.getDebitadoDe() + " y " + datosTransf.getAcreditadoA());

        // Busca la información completa de las tarjetas
        ArrayList<TarjetaModel> debitadoDe = tarjetaRepository.findByNumero(datosTransf.getDebitadoDe());
        ArrayList<TarjetaModel> acreditadoA = tarjetaRepository.findByNumero(datosTransf.getAcreditadoA());

        // Comprueba que ambas tarjetas existan
        if (debitadoDe.isEmpty() || acreditadoA.isEmpty()) {
            System.out.println("Tarjetas no encontradas!!");
            return null;
        }

        System.out.println("Tarjetas encontradas");

        // Comprueba que el id del usuario al que pertenece la tarjeta es el mismo que el
        // id del usuario que generó el token (intenta prevenir la manipulación del Js)
        if (debitadoDe.get(0).getUsuario().getId() != tarjetaRepository.findById(tarjetaId).get().getUsuario().getId()) {
            System.out.println("ID del usuario segun tarjeta: " + debitadoDe.get(0).getUsuario().getId());
            System.out.println("ID del token: " +  tarjetaRepository.findById(tarjetaId).get().getUsuario().getId());
            System.out.println("Alteración del Js detectada. Transaccion cancelada.");
            return null;
        }

        System.out.println("Comprobacion anti-manipulacion del Js superada");

        // Comprueba si hay dinero en la tarjeta para hacer la transferencia
        float nuevoSaldo = debitadoDe.get(0).getSaldo() - datosTransf.getMonto();
        if (nuevoSaldo >= 0) {

            System.out.println("Iniciando envio de dinero");

            // Se debita el dinero
            debitadoDe.get(0).setSaldo(nuevoSaldo);
            tarjetaRepository.save(debitadoDe.get(0));

            System.out.println("A " + debitadoDe.get(0).getNumero() + " se le debito $" + datosTransf.getMonto() + ". Su nuevo saldo es: $" + nuevoSaldo);

            // Se acredita el dinero
            acreditadoA.get(0).setSaldo(acreditadoA.get(0).getSaldo() + datosTransf.getMonto());
            tarjetaRepository.save(acreditadoA.get(0));

            System.out.println("A " + acreditadoA.get(0).getNumero() + " se le acredito $" + datosTransf.getMonto() + ". Su nuevo saldo es: $" + acreditadoA.get(0).getSaldo());

            // Crea el registro de la transferencia
            TransferenciaModel nuevaTransferencia = new TransferenciaModel();
            Date fechaActual = new Date(System.currentTimeMillis());
            nuevaTransferencia.setFecha(fechaActual);
            nuevaTransferencia.setMonto(datosTransf.getMonto());
            nuevaTransferencia.setDebitadoDeTarjeta(debitadoDe.get(0));
            nuevaTransferencia.setDebitadoDeUsuario(debitadoDe.get(0).getUsuario());
            nuevaTransferencia.setAcreditadoATarjeta(acreditadoA.get(0));
            nuevaTransferencia.setAcreditadoAUsuario(acreditadoA.get(0).getUsuario());

            System.out.println("Transferencia finalizada");

            // Guarda el registro
            return transferenciaRepository.save(nuevaTransferencia);
        }

        return null;

    }

}
