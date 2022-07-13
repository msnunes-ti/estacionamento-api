package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;

public class ObterEntradaClienteDTO {

    private SituacaoEnum situacaoEnum;

    private String placa;

    public SituacaoEnum getSituacaoEnum() {
        return situacaoEnum;
    }

    public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
        this.situacaoEnum = situacaoEnum;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}

