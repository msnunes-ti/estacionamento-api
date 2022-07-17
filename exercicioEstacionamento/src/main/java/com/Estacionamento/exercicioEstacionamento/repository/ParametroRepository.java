package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroRepository extends JpaRepository<Parametro, Long> {

    Optional<Parametro> findFirst();
}
