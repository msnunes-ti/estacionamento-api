package com.Estacionamento.exercicioEstacionamento.repository;

import com.Estacionamento.exercicioEstacionamento.model.Financeiro;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FinanceiroRepository extends PagingAndSortingRepository<Financeiro, Integer> {

}
