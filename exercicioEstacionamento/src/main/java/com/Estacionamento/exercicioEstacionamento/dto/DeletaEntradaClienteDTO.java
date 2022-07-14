package com.Estacionamento.exercicioEstacionamento.dto;

public class DeletaEntradaClienteDTO {

    private String Placa;

    private long id;

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
