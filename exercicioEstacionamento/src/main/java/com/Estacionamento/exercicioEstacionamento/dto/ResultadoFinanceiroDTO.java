package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.model.Financeiro;

import java.math.BigDecimal;

public class ResultadoFinanceiroDTO {

    int registros;

    BigDecimal valorTotal;

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
