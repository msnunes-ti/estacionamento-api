package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.*;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.services.EntradaClienteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/estacionamento")
public class EntradaController {

    @Autowired
    EntradaClienteService entradaClienteService;

    @GetMapping(path = "/abertos")
    public List<EntradaCliente> obterAbertos() {
        ObterEntradaClienteDTO obterEntradaClienteDTO = new ObterEntradaClienteDTO();
        obterEntradaClienteDTO.setEntradaClientes((List<EntradaCliente>) entradaClienteService.obterAbertos());
        return obterEntradaClienteDTO.getEntradaClientes();
    }

    @GetMapping(path = "/finalizados")
    public List<EntradaCliente> obterFinalizados() {
        ObterEntradaClienteDTO obterEntradaClienteDTO = new ObterEntradaClienteDTO();
        obterEntradaClienteDTO.setEntradaClientes(entradaClienteService.obterFinalizados());
        return obterEntradaClienteDTO.getEntradaClientes();
    }

    @GetMapping(path = "/{placa}/obter")
    public List<EntradaCliente> obterPelaPlaca(@PathVariable @Valid String placa) {
        ObterEntradaClienteDTO obterEntradaClienteDTO = new ObterEntradaClienteDTO();
        obterEntradaClienteDTO.setPlaca(placa);
        return entradaClienteService.obterPelaPlaca(obterEntradaClienteDTO.getPlaca());
    }

    @GetMapping
    public List<EntradaCliente> obterPorDatas(@RequestParam(required = false) String dataInicial, @RequestParam(required = false) String dataFinal, @RequestParam(required = false) SituacaoEnum situacaoEnum) {
        return entradaClienteService.obterPorFiltros(dataInicial, dataFinal, situacaoEnum);
    }

    @PostMapping
    public @ResponseBody EntradaClienteDTO criaNovaEntrada(@RequestBody @Valid @NotNull CadastraEntradaClienteDTO criaEntradaClienteDTO) {
        return entradaClienteService.novaEntrada(criaEntradaClienteDTO);
    }

    @PutMapping(path = "/{placa}/saida")
    public SaidaEntradaClienteDTO registraSaida(@PathVariable String placa) {
        return entradaClienteService.registraSaida(placa);
    }

    @PutMapping(path = "/{placa}")
    public EntradaCliente alterarRegistro(@Valid @RequestBody AlteraEntradaClienteDTO alteraEntradaClienteDTO, @PathVariable String placa) {
        return entradaClienteService.alterarRegistro(alteraEntradaClienteDTO, placa);
    }

    @DeleteMapping(path = "/{placa}")
    public void deletarRegistro(@PathVariable String placa) {
        DeletaEntradaClienteDTO deletaEntradaClienteDTO = new DeletaEntradaClienteDTO();
        deletaEntradaClienteDTO.setPlaca(placa);
        entradaClienteService.deletarRegistro(deletaEntradaClienteDTO.getPlaca());
    }

    @DeleteMapping(path = "/{placa}/{codigo}")
    public void deletarRegistroPeloId(@PathVariable long codigo, @PathVariable String placa) {
        DeletaEntradaClienteDTO deletaEntradaClienteDTO = new DeletaEntradaClienteDTO();
        deletaEntradaClienteDTO.setId(codigo);
        deletaEntradaClienteDTO.setPlaca(placa);
        entradaClienteService.deletarRegistroPeloId(deletaEntradaClienteDTO.getId(), deletaEntradaClienteDTO.getPlaca());
    }

}
