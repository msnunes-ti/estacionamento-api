package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EntradaRepository extends JpaRepository<EntradaCliente, Long> {

    List<EntradaCliente> findByPlacaContainingIgnoreCase(String placa);

    List<EntradaCliente> findByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    List<EntradaCliente> findBySaidaIsNull();

    List<EntradaCliente> findBySaidaNotNull();

    long countByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    void deleteByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    List<EntradaCliente> findDateIsBetweenStartAnd (LocalDateTime dataEntrada, LocalDateTime dataSaida);

cd id
}
