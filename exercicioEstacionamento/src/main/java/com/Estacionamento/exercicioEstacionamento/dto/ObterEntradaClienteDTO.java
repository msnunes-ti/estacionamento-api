package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ObterEntradaClienteDTO {

    private List<EntradaCliente> entradaClientes;

    @NotNull
    @NotBlank
    private String placa;

    private SituacaoEnum situacaoEnum;

    public List<EntradaCliente> getEntradaClientes() {
        return entradaClientes;
    }

    public void setEntradaClientes(List<EntradaCliente> entradaClientes) {
        this.entradaClientes = entradaClientes;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public SituacaoEnum getSituacaoEnum() {
        return situacaoEnum;
    }

    public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
        this.situacaoEnum = situacaoEnum;
    }
}

