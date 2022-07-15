package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SaidaEntradaClienteDTO {

    @NotNull
    @NotBlank
    private String placa;

    private EntradaCliente entradaCliente;

    private BigDecimal valorHora;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

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
