package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;

import java.math.BigDecimal;

public class SaidaEntradaClienteDTO {

    private EntradaCliente entradaCliente;

    private BigDecimal valorHora;

    public EntradaCliente getEntradaCliente() {
        return entradaCliente;
    }

    public void setEntradaCliente(EntradaCliente entradaCliente) {
        this.entradaCliente = entradaCliente;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }
}
