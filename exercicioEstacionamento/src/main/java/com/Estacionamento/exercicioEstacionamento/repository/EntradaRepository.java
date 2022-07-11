package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface EntradaRepository extends PagingAndSortingRepository<EntradaCliente, Integer> {

    public List<EntradaCliente> findByPlacaContainingIgnoreCase(String placa);

    public List<EntradaCliente> findByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    public List<EntradaCliente> findBySaidaIsNull();

    public List<EntradaCliente> findBySaidaNotNull();

    public long countByPlacaIgnoreCaseAndSaidaIsNull(String placa);

    public void deleteByPlacaIgnoreCaseAndSaidaIsNull(String placa);

}
