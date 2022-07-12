package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ParametroRepository extends PagingAndSortingRepository<Parametro, BigDecimal> {

    Optional<Parametro> findById(Long id);

}
