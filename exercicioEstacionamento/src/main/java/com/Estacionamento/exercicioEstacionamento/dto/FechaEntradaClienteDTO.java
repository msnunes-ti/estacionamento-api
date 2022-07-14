package com.Estacionamento.exercicioEstacionamento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FechaEntradaClienteDTO {

    private LocalDateTime saida;

    private BigDecimal valor;

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
