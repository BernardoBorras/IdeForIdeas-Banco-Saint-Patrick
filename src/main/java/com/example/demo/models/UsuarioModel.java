package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mis tarjetas
    @JsonIgnoreProperties({ "usuario" })
    @OneToMany(mappedBy = "usuario")
    private List<TarjetaModel> tarjetas;

    // El dinero que envié
    @JsonIgnoreProperties({ "debitadoDeUsuario", "debitadoDeTarjeta" })
    @Column(name = "transferencias_recibidas")
    @OneToMany(mappedBy = "debitadoDeUsuario")
    private List<TransferenciaModel> transfEnviadas;

    // El dinero que recibí
    @JsonIgnoreProperties({ "acreditadoAUsuario", "acreditadoATarjeta" })
    @Column(name = "transferencias_enviadas")
    @OneToMany(mappedBy = "acreditadoAUsuario")
    private List<TransferenciaModel> transfRecibidas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TarjetaModel> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaModel> tarjetas) {
        this.tarjetas = tarjetas;
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
