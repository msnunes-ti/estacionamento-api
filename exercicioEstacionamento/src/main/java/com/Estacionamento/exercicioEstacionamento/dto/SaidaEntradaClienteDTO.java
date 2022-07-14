package com.Estacionamento.exercicioEstacionamento.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaidaEntradaClienteDTO {

    @NotNull
    @NotBlank
    private String placa;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
