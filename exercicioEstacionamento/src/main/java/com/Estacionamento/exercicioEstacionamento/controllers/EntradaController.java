package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.CadastraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.services.EntradaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/estacionamento")
public class EntradaController {

    @Autowired
    EntradaClienteService entradaClienteService;

    @PostMapping
    public @ResponseBody EntradaCliente criaNovaEntrada(@RequestBody @Valid CadastraEntradaClienteDTO criaEntradaClienteDTO) {
        EntradaCliente entradaCliente = new EntradaCliente();
        entradaCliente.setModelo(criaEntradaClienteDTO.getModelo());
        entradaCliente.setPlaca(criaEntradaClienteDTO.getPlaca());
        return entradaClienteService.novaEntrada(entradaCliente);
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
        return entradaClienteService.alterarRegistro(alteraEntradaClienteDTO, placa);
    }

    @DeleteMapping(path = "/{placa}")
    public void deletarRegistro(@PathVariable String placa) {

    }

    @DeleteMapping(path = "/{placa}/{codigo}")
    public void deletarRegistroPeloId(@PathVariable int codigo, @PathVariable String placa) {

    }

}
