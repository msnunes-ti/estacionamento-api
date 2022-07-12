package com.Estacionamento.exercicioEstacionamento.dto;

import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import com.Estacionamento.exercicioEstacionamento.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CadastraParametroDTO {

    @Autowired(required = true)
    private ParametroRepository parametroRepository;

    @NotNull
    private BigDecimal parametro;

    public CadastraParametroDTO() {
    }

    public Iterable<Parametro> consultaTodos() {
        return parametroRepository.findAll();
    }

    public Optional<Parametro> consultaPorId(Long id) {
        return parametroRepository.findById(id);
    }

    public void criaParametro(BigDecimal valor) {
        List<Parametro> parametrolocalizado = (List<Parametro>) consultaTodos();
        if (parametrolocalizado.size() != 0) {
            throw new RuntimeException("O parametro Já foi criado");
        }
        Parametro parametro1 = new Parametro();
        parametro1.setValorHora(valor);
        parametroRepository.save(parametro1);
    }

    public void alteraParametro(Long id, Double novoValor) {
        Optional<Parametro> parametro1 = parametroRepository.findById(id);
        parametro1.orElseThrow().setValorHora(BigDecimal.valueOf(novoValor));
    }
}
