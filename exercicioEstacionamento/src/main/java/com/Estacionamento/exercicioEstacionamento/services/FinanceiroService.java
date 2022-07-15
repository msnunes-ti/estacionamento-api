package com.Estacionamento.exercicioEstacionamento.services;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.model.Financeiro;
import com.Estacionamento.exercicioEstacionamento.repository.EntradaRepository;
import com.Estacionamento.exercicioEstacionamento.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinanceiroService {

    @Autowired
    EntradaRepository entradaRepository;

    public Financeiro relatorioGeralFinanceiro() {
        int quantidadeDeRegistros = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<EntradaCliente> todosJaFinalizados = obterFinalizados();
        if (todosJaFinalizados.isEmpty()) {
            throw new RuntimeException("Não foram encontrados lançamentos finalizados");
        }
        for (EntradaCliente e : todosJaFinalizados) {
            valorTotal = valorTotal.add(e.getValor());
            quantidadeDeRegistros ++;
        }
        Financeiro resultado = new Financeiro();
        resultado.setNumeroDeRegistros(quantidadeDeRegistros);
        resultado.setValorTotal(valorTotal);
        return resultado;
    }

    public List<EntradaCliente> obterFinalizados() {
        return entradaRepository.findBySaidaNotNull();
    }
}
