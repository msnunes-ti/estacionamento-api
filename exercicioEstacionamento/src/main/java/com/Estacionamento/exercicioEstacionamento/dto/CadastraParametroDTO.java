package com.Estacionamento.exercicioEstacionamento.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CadastraParametroDTO {

    @NotNull
    private BigDecimal valorHora;

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }
}
