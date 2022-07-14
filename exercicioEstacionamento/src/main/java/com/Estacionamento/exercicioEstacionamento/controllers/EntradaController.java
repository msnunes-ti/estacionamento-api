package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.CadastraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.FechaEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.ObterEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.services.EntradaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum.*;

@RestController
@RequestMapping(path = "/estacionamento")
public class EntradaController {

    @Autowired
    EntradaClienteService entradaClienteService;

    @GetMapping(path = "/{situacaoEnum}")
    public List<EntradaCliente> obterComSituacao(@PathVariable SituacaoEnum situacaoEnum) {
        return (List<EntradaCliente>) entradaClienteService.obterTodos(situacaoEnum);
    }

    @GetMapping(path = "/abertos")
    public List<EntradaCliente> obterAbertos() {
        return (List<EntradaCliente>) entradaClienteService.obterAbertos();
    }

    @GetMapping(path = "/finalizados")
    public List<EntradaCliente> obterFinalizados() {
        return (List<EntradaCliente>) entradaClienteService.obterFinalizados();
    }

    @GetMapping(path = "/{placa}/obter")
    public List<EntradaCliente> obterPelaPlaca(@PathVariable @Valid String placa) {
        ObterEntradaClienteDTO obterEntradaClienteDTO = new ObterEntradaClienteDTO();
        obterEntradaClienteDTO.setPlaca(placa);
        return (List<EntradaCliente>) entradaClienteService.obterPelaPlaca(obterEntradaClienteDTO.getPlaca());
    }

    @PostMapping
    public @ResponseBody EntradaCliente criaNovaEntrada(@RequestBody @Valid CadastraEntradaClienteDTO criaEntradaClienteDTO) {
        EntradaCliente entradaCliente = new EntradaCliente();
        entradaCliente.setModelo(criaEntradaClienteDTO.getModelo());
        entradaCliente.setPlaca(criaEntradaClienteDTO.getPlaca());
        return entradaClienteService.novaEntrada(entradaCliente);
    }

    @PutMapping(path = "/{placa}/saida")
    public void registraSaida(@PathVariable String placa) {
        entradaClienteService.registraSaida(placa);
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
