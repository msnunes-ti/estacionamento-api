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

   List<EntradaCliente> findByEntradaIsBetweenAndSaidaIsNull (LocalDateTime entrada, LocalDateTime saida);

   List<EntradaCliente> findByEntradaIsBetweenAndSaidaNotNull (LocalDateTime entrada, LocalDateTime saida);

    List<EntradaCliente> findByEntradaIsBetween (LocalDateTime entrada, LocalDateTime saida);


}
