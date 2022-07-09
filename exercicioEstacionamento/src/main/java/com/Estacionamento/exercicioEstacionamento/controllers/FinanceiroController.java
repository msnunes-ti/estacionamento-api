package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.model.Financeiro;
import com.Estacionamento.exercicioEstacionamento.repository.FinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/financeiro")
public class FinanceiroController {

    @Autowired
    private FinanceiroRepository financeiroRepository;

    @GetMapping
    public Financeiro relarórioGeralFinanceiro() {
        List<Financeiro> todosJaFinalizados = new ArrayList<>();
        todosJaFinalizados = (List<Financeiro>) financeiroRepository.findAll();
        if (todosJaFinalizados.isEmpty()) {
            throw new RuntimeException("Não foram encontrados lançamentos finalizados");
        }
        for (Financeiro e : todosJaFinalizados) {
            System.out.printf("Teste");

        }

        return null;
    }


}
