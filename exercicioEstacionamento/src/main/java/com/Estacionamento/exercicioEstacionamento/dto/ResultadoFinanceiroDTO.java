package com.Estacionamento.exercicioEstacionamento.dto;

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
