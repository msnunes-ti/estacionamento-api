package com.Estacionamento.exercicioEstacionamento.model;

import java.math.BigDecimal;

public class Financeiro {

    private int numeroDeRegistros;

    private BigDecimal valorTotal;

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
