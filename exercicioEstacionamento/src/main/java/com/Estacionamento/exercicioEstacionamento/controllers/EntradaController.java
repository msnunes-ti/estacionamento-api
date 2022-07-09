package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public Iterable<EntradaCliente> obterTodos() {
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
        entradaCliente.setValor(BigDecimal.valueOf(5 * (horas + 1))); // 5 é o valor da hora do estacionamento
        entradaRepository.save(entradaCliente);
        return entradaCliente;
    }

}
