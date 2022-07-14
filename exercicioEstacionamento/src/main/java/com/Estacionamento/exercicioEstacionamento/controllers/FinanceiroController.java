package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.ResultadoFinanceiroDTO;
import com.Estacionamento.exercicioEstacionamento.model.Financeiro;
import com.Estacionamento.exercicioEstacionamento.services.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/estacionamento/financeiro")
public class FinanceiroController {

    @Autowired
    FinanceiroService financeiroService;

    @GetMapping
    public ResultadoFinanceiroDTO resultadoFinanceiroGeral() {
        Financeiro financeiro = financeiroService.relatorioGeralFinanceiro();
        ResultadoFinanceiroDTO resultadoFinanceiroDTO = new ResultadoFinanceiroDTO();
        resultadoFinanceiroDTO.setRegistros(financeiro.getNumeroDeRegistros());
        resultadoFinanceiroDTO.setValorTotal(financeiro.getValorTotal());
        return resultadoFinanceiroDTO;
    }

}
