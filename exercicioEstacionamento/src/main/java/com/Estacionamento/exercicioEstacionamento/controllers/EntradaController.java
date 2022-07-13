package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/estacionamento")
public class EntradaController {

    @PostMapping
    public @ResponseBody EntradaCliente novaEntrada(@Valid @RequestBody EntradaCliente entradaCliente) {

        return null;
    }

    @GetMapping
    public Iterable<EntradaCliente> obterTodos(@RequestParam(required = false) SituacaoEnum situacao) {


        return null;
    }

    @GetMapping(path = "/abertos")
    public Iterable<EntradaCliente> obterAbertos() {


        return null;
    }

    @GetMapping(path = "/finalizados")
    public Iterable<EntradaCliente> obterFinalizados() {

        return null;
    }

    @GetMapping(path = "/{placa}/pesquisa")
    public Iterable<EntradaCliente> obterPelaPlaca(@PathVariable String placa) {

        return null;
    }

    @PutMapping(path = "/{placa}/saida")
    public EntradaCliente registraSaida(@PathVariable String placa) {

        return null;
    }

    @PutMapping(path = "/{placa}")
    public EntradaCliente alterarRegistro(@Valid @RequestBody AlteraEntradaClienteDTO alteraEntradaClienteDTO, @PathVariable String placa) {
        
        return null;
    }

    @DeleteMapping(path = "/{placa}")
    public void deletarRegistro(@PathVariable String placa) {

    }

    @DeleteMapping(path = "/{placa}/{codigo}")
    public void deletarRegistroPeloId(@PathVariable int codigo, @PathVariable String placa) {

    }

}
