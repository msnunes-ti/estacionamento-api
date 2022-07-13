package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.CadastraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.ObterEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.services.EntradaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum.*;

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

    @GetMapping(path = "/{situacaoEnum}")
    public @ResponseBody List<EntradaCliente> obterComSituacao(@RequestBody(required = false) @PathVariable SituacaoEnum situacaoEnum) {
    Optional<ObterEntradaClienteDTO> obterEntradaClienteDTO = Optional.of(new ObterEntradaClienteDTO());
    if (!obterEntradaClienteDTO.isPresent()) {
        ObterEntradaClienteDTO obterEntradaClienteDTO1 = new ObterEntradaClienteDTO();
        obterEntradaClienteDTO1.setSituacaoEnum(TODOS);
    }
    return (List<EntradaCliente>) entradaClienteService.obterTodos(situacaoEnum);
    }

    @GetMapping(path = "/abertos")
    public List<EntradaCliente> obterAbertos() {


        return null;
    }

    @GetMapping(path = "/finalizados")
    public List<EntradaCliente> obterFinalizados() {

        return null;
    }

    @GetMapping(path = "/{placa}/pesquisa")
    public List<EntradaCliente> obterPelaPlaca(@PathVariable String placa) {

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
