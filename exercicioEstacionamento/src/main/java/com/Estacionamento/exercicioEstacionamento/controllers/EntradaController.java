package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.*;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.services.EntradaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/estacionamento")
public class EntradaController {

    @Autowired
    EntradaClienteService entradaClienteService;

    @GetMapping(path = "/{situacaoEnum}")
    public List<EntradaCliente> obterComSituacao(@PathVariable SituacaoEnum situacaoEnum) {
        ObterEntradaClienteDTO obterEntradaClienteDTO = new ObterEntradaClienteDTO();
        obterEntradaClienteDTO.setSituacaoEnum(situacaoEnum);
        obterEntradaClienteDTO.setEntradaClientes(entradaClienteService.obterTodos(obterEntradaClienteDTO.getSituacaoEnum()));
        return obterEntradaClienteDTO.getEntradaClientes();
    }

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

    @GetMapping(path = "/{dataEntrada}/{dataSaida}")
    public List<EntradaCliente> obterPorDatas(@PathVariable @Valid @RequestBody ObterPorDatasEntradaClienteDTO obterPorDatasEntradaClienteDTO) {
        LocalDate dataEntrada = obterPorDatasEntradaClienteDTO.getDataEntrada();
        LocalDate dataSaida = obterPorDatasEntradaClienteDTO.getDataSaida();
        return null;
//        return entradaClienteService.obterPorDatas(dataEntrada, dataSaida);
    }

    @PostMapping
    public @ResponseBody EntradaCliente criaNovaEntrada(@RequestBody @Valid CadastraEntradaClienteDTO criaEntradaClienteDTO) {
        EntradaCliente entradaCliente = new EntradaCliente();
        entradaCliente.setModelo(criaEntradaClienteDTO.getModelo());
        entradaCliente.setPlaca(criaEntradaClienteDTO.getPlaca());
        return entradaClienteService.novaEntrada(entradaCliente);
    }

    @PutMapping(path = "/{placa}/saida")
    public EntradaCliente registraSaida(@PathVariable String placa) {
        SaidaEntradaClienteDTO saidaEntradaClienteDTO = new SaidaEntradaClienteDTO();
        saidaEntradaClienteDTO.setPlaca(placa);
        return entradaClienteService.registraSaida(saidaEntradaClienteDTO.getPlaca());
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
