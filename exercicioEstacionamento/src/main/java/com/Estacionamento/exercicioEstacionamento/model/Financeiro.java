package com.Estacionamento.exercicioEstacionamento.model;

import java.math.BigDecimal;

public class Financeiro {

    private Long numeroDeRegistros;

    private BigDecimal valorTotal;

    public Long getNumeroDeRegistros() {
        return numeroDeRegistros;
    }

    public void setNumeroDeRegistros(Long numeroDeRegistros) {
        this.numeroDeRegistros = numeroDeRegistros;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
