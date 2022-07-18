package com.Estacionamento.exercicioEstacionamento.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;

public class CadastraParametroDTO {

    @NotNull
    private BigDecimal valorHora;

    @NotNull
    private Time horaInicio;

    @NotNull
    private Time horaFim;

    private Integer tolerancia;

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }
}
