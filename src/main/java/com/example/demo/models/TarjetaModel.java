package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tarjetas")
public class TarjetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1234-5678-8765-4321
    @Column(nullable = false, unique = true)
    private String numero;

    // PIN: 1234
    @Column(nullable = false)
    private String pin;

    // SALDO INICIAL: $4000
    @Column(nullable = false)
    private Float saldo;

    // Muestra a quien pertenece esta tarjeta
    @JsonIgnoreProperties({ "tarjetas" })
    @ManyToOne()
    @JoinColumn(name = "pertenece_a")
    private UsuarioModel usuario;

    // Las transferencias debitadas de esta tarjeta
    @JsonIgnoreProperties({ "debitadoDeUsuario", "debitadoDeTarjeta" })
    @Column(name = "transferencias_recibidas")
    @OneToMany(mappedBy = "debitadoDeTarjeta")
    private List<TransferenciaModel> transfEnviadas;

    // Las transferencias acreditadas a esta tarjeta
    @JsonIgnoreProperties({ "acreditadoAUsuario", "acreditadoATarjeta" })
    @Column(name = "transferencias_enviadas")
    @OneToMany(mappedBy = "acreditadoATarjeta")
    private List<TransferenciaModel> transfRecibidas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public List<TransferenciaModel> getTransfRecibidas() {
        return transfRecibidas;
    }

    public void setTransfRecibidas(List<TransferenciaModel> transfRecibidas) {
        this.transfRecibidas = transfRecibidas;
    }

    public List<TransferenciaModel> getTransfEnviadas() {
        return transfEnviadas;
    }

    public void setTransfEnviadas(List<TransferenciaModel> transfEnviadas) {
        this.transfEnviadas = transfEnviadas;
    }

}
