package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EntradaRepository extends PagingAndSortingRepository<EntradaCliente, Integer> {

    public List<EntradaCliente> findByPlacaContainingIgnoreCase(String placa);

    public List<EntradaCliente> findByPlacaIgnoreCaseAndSaidaIsNull(String placa);



}
