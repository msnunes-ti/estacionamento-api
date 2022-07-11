package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.ResultadoFinanceiroDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.repository.EntradaRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum.ABERTO;
import static com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum.FECHADO;

@RestController
@RequestMapping(path = "/estacionamento")
public class EntradaController {

    @Autowired
    private EntradaRepository entradaRepository;

    @PostMapping
    public @ResponseBody EntradaCliente novaEntrada(@Valid @RequestBody EntradaCliente entradaCliente) {
        entradaCliente.setEntrada(LocalDateTime.now());
        entradaRepository.save(entradaCliente);
        return entradaCliente;
    }

    @GetMapping(path = "{situacaoEnum}")
    public Iterable<EntradaCliente> obterTodos(@PathVariable SituacaoEnum situacaoEnum) {
        switch (situacaoEnum) {
            case FECHADO:
                return entradaRepository.findBySaidaNotNull();
            case ABERTO: 
                return entradaRepository.findBySaidaIsNull();
            }
        return entradaRepository.findAll();
    }

    @GetMapping(path = "/abertos")
    public Iterable<EntradaCliente> obterAbertos() {
        List<EntradaCliente> todos = (List<EntradaCliente>) entradaRepository.findAll();
        List<EntradaCliente> abertos = new ArrayList<>();
        for (EntradaCliente e : todos) {
            if (e.getSaida() == null) {
                abertos.add(e);
            }
        }
        return abertos;
    }

    @GetMapping(path = "/finalizados")
    public Iterable<EntradaCliente> obterFinalizados() {
        List<EntradaCliente> todos = (List<EntradaCliente>) entradaRepository.findAll();
        List<EntradaCliente> finalizados = new ArrayList<>();
        for (EntradaCliente e : todos) {
            if (e.getSaida() != null) {
                finalizados.add(e);
            }
        }
        return finalizados;
    }

    @GetMapping(path = "/{placa}/pesquisa")
    public Iterable<EntradaCliente> obterPelaPlaca(@PathVariable String placa) {
        return entradaRepository.findByPlacaContainingIgnoreCase(placa);
    }

    @PutMapping(path = "/{placa}/saida")
    public EntradaCliente registraSaida(@PathVariable String placa) {
        List<EntradaCliente> entradaClientes = entradaRepository.findByPlacaIgnoreCaseAndSaidaIsNull(placa);
        if (entradaClientes.size() > 1) {
            throw new RuntimeException("Existe mais de um registro aberto para essa placa.");
        }
        if (entradaClientes.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado.");
        }
        EntradaCliente entradaCliente = entradaClientes.get(0);
        entradaCliente.setSaida(LocalDateTime.now());
        int horas = (int) entradaCliente.getEntrada().until(entradaCliente.getSaida(), ChronoUnit.HOURS);
        entradaCliente.setValor(BigDecimal.valueOf(5L * (horas + 1))); // 5.0 é o valor da hora do estacionamento
        entradaRepository.save(entradaCliente);
        return entradaCliente;
    }

    @GetMapping(path = "/financeiro")
    public ResultadoFinanceiroDTO relatorioGeralFinanceiro() {
        int quantidadeDeRegistros = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<EntradaCliente> todosJaFinalizados = new ArrayList<>();
        todosJaFinalizados = (List<EntradaCliente>) obterFinalizados();
        if (todosJaFinalizados.isEmpty()) {
            throw new RuntimeException("Não foram encontrados lançamentos finalizados");
        }
        for (EntradaCliente e : todosJaFinalizados) {
            valorTotal = valorTotal.add(e.getValor());
            quantidadeDeRegistros ++;
        }
        ResultadoFinanceiroDTO resultado = new ResultadoFinanceiroDTO();
        resultado.setRegistros(quantidadeDeRegistros);
        resultado.setValorTotal(valorTotal);
        return resultado;
    }

    @PutMapping(path = "/{placa}")
    public EntradaCliente alterarRegistro(@Valid @RequestBody AlteraEntradaClienteDTO alteraEntradaClienteDTO, @PathVariable String placa) {

        long estacionados = entradaRepository.countByPlacaIgnoreCaseAndSaidaIsNull(placa);
        if (estacionados > 1){
            throw new RuntimeException("Foram encontrados mais de um registros abertos com essa placa, favor verificar!");
        }
        if (estacionados == 0) {
            throw new RuntimeException("Não foi encontrado nenhum registro de entrada desse veículo.");
        }
        List<EntradaCliente> entradaClientes = entradaRepository.findByPlacaIgnoreCaseAndSaidaIsNull(placa);
        EntradaCliente cliente = entradaClientes.get(0);
        cliente.setPlaca(alteraEntradaClienteDTO.getPlaca());
        cliente.setModelo(alteraEntradaClienteDTO.getModelo());
        cliente.setEntrada(alteraEntradaClienteDTO.getEntrada());
        entradaRepository.save(cliente);
        return cliente;
    }

}
