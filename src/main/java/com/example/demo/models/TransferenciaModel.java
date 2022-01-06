package com.example.demo.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transferencias")
public class TransferenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Debitado de Usuario
    @JsonIgnoreProperties({ "transfRecibidas", "transfEnviadas", "tarjetas" })
    @ManyToOne()
    @JoinColumn(name = "debitado_de_usuario", nullable = false)
    private UsuarioModel debitadoDeUsuario;

    // Acreditado a Usuario
    @JsonIgnoreProperties({ "transfRecibidas", "transfEnviadas", "tarjetas" })
    @ManyToOne()
    @JoinColumn(name = "acreditado_a_usuario", nullable = false)
    private UsuarioModel acreditadoAUsuario;

    // Debitado de Tarjeta
    @JsonIgnoreProperties({ "transfRecibidas", "transfEnviadas", "usuario", "saldo", "pin", "id" })
    @ManyToOne()
    @JoinColumn(name = "debitado_de_tarjeta", nullable = false)
    private TarjetaModel debitadoDeTarjeta;

    // Acreditado a Tarjeta
    @JsonIgnoreProperties({ "transfRecibidas", "transfEnviadas", "usuario", "saldo", "pin", "id" })
    @ManyToOne()
    @JoinColumn(name = "acreditado_a_tarjeta", nullable = false)
    private TarjetaModel acreditadoATarjeta;

    // Monto
    @Column(nullable = false)
    private Float monto;

    // Fecha de la transferencia
    @Column(nullable = false)
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getDebitadoDeUsuario() {
        return debitadoDeUsuario;
    }

    public void setDebitadoDeUsuario(UsuarioModel debitadoDeUsuario) {
        this.debitadoDeUsuario = debitadoDeUsuario;
    }

    public UsuarioModel getAcreditadoAUsuario() {
        return acreditadoAUsuario;
    }

    public void setAcreditadoAUsuario(UsuarioModel acreditadoAUsuario) {
        this.acreditadoAUsuario = acreditadoAUsuario;
    }

    public TarjetaModel getDebitadoDeTarjeta() {
        return debitadoDeTarjeta;
    }

    public void setDebitadoDeTarjeta(TarjetaModel debitadoDeTarjeta) {
        this.debitadoDeTarjeta = debitadoDeTarjeta;
    }

    public TarjetaModel getAcreditadoATarjeta() {
        return acreditadoATarjeta;
    }

    public void setAcreditadoATarjeta(TarjetaModel acreditadoATarjeta) {
        this.acreditadoATarjeta = acreditadoATarjeta;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
