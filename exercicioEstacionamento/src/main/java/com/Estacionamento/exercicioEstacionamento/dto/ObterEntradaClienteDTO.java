package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;

public class ObterEntradaClienteDTO {

    private SituacaoEnum situacaoEnum;

    public SituacaoEnum getSituacaoEnum() {
        return situacaoEnum;
    }

    public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
        this.situacaoEnum = situacaoEnum;
    }
}

