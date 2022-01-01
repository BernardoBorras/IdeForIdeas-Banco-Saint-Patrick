package com.example.demo.models;

import java.util.Date;

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

    // Debitado de
    @JsonIgnoreProperties({ "transfRecibidas, transfEnviadas" })
    @ManyToOne()
    @JoinColumn(name = "debitado_de", nullable = false)
    private UsuarioModel debitadoDe;

    // Acreditado a
    @JsonIgnoreProperties({ "transfRecibidas, transfEnviadas" })
    @ManyToOne()
    @JoinColumn(name = "acreditado_a", nullable = false)
    private UsuarioModel acreditadoA;

    // Fecha de la transferencia
    @Column(nullable = false)
    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getDebitadoDe() {
        return debitadoDe;
    }

    public void setDebitadoDe(UsuarioModel debitadoDe) {
        this.debitadoDe = debitadoDe;
    }

    public UsuarioModel getAcreditadoA() {
        return acreditadoA;
    }

    public void setAcreditadoA(UsuarioModel acreditadoA) {
        this.acreditadoA = acreditadoA;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
