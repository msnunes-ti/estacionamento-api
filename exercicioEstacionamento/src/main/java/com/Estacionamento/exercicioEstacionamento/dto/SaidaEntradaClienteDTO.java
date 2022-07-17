package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SaidaEntradaClienteDTO {

    @NotNull
    @NotBlank
    private String placa;

    private EntradaClienteDTO entradaClienteDTO;

    private BigDecimal valorHora;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public EntradaClienteDTO getEntradaCliente() {
        return entradaClienteDTO;
    }

    public void setEntradaCliente(EntradaClienteDTO entradaCliente) {
        this.entradaClienteDTO = entradaCliente;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }
}
