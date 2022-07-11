package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EntradaRepository extends PagingAndSortingRepository<EntradaCliente, Integer> {

    List<EntradaCliente> findByPlacaContainingIgnoreCase(String placa);

    List<EntradaCliente> findByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    List<EntradaCliente> findBySaidaIsNull();

    List<EntradaCliente> findBySaidaNotNull();

    long countByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    void deleteByPlacaIgnoreCaseAndSaidaIsNull(String placa);

}
