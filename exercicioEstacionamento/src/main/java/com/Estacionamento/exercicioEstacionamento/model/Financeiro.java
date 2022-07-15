package com.Estacionamento.exercicioEstacionamento.model;

import java.math.BigDecimal;

public class Financeiro {

    private BigDecimal valorHora;

    private int numeroDeRegistros;

    private BigDecimal valorTotal;

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public int getNumeroDeRegistros() { return numeroDeRegistros;
    }

    public void setNumeroDeRegistros(int numeroDeRegistros) {
        this.numeroDeRegistros = numeroDeRegistros;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
