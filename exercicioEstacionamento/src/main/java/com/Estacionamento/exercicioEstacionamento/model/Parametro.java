package com.Estacionamento.exercicioEstacionamento.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_hora")
    private BigDecimal valorHora;

    public Parametro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }
}
