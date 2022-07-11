package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import com.Estacionamento.exercicioEstacionamento.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CadastraParametroDTO {

    @Autowired
    private ParametroRepository parametroRepository;

    @NotNull
    private Long parametro;

    public CadastraParametroDTO() {
    }

    public Parametro criaParametro(BigDecimal valor) {
        Parametro parametro1 = new Parametro();
        parametro1.setValorHora(valor);
        return parametroRepository.save(parametro1);
    }

    public void setParametro(Long parametro) {
        this.parametro = parametro;
    }
}
