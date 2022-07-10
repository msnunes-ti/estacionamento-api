package com.Estacionamento.exercicioEstacionamento.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AlteraEntradaClienteDTO {

    @NotNull
    String modelo;

    @NotNull
    String placa;

    @NotNull
    LocalDateTime entrada;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }
}
