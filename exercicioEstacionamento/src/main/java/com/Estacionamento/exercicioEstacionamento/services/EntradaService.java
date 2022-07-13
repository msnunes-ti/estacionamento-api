package com.Estacionamento.exercicioEstacionamento.services;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;


    public EntradaCliente novaEntrada(@Valid EntradaCliente entradaCliente) {
        entradaCliente.setEntrada(LocalDateTime.now());
        entradaRepository.save(entradaCliente);
        return entradaCliente;
    }

    public Iterable<EntradaCliente> obterTodos(SituacaoEnum situacao) {
        return switch (Optional.ofNullable(situacao).orElse(SituacaoEnum.TODOS)) {
            case FECHADO -> entradaRepository.findBySaidaNotNull();
            case ABERTO -> entradaRepository.findBySaidaIsNull();
            default -> entradaRepository.findAll();
        };
    }

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

    public Iterable<EntradaCliente> obterPelaPlaca(String placa) {
        return entradaRepository.findByPlacaContainingIgnoreCase(placa);
    }

    public EntradaCliente registraSaida(String placa) {
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



    public EntradaCliente alterarRegistro(@Valid AlteraEntradaClienteDTO alteraEntradaClienteDTO, String placa) {

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

    public void deletarRegistro(String placa) {
        if (entradaRepository.countByPlacaIgnoreCaseAndSaidaIsNull(placa) == 0) {
            throw new RuntimeException("Não foram encontrados registros em aberto para essa placa.");
        }
        entradaRepository.deleteByPlacaIgnoreCaseAndSaidaIsNull(placa);
    }

    public void deletarRegistroPeloId(@PathVariable int codigo, @PathVariable String placa) {
        EntradaCliente entradaCliente = entradaRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("Código (id) não encontrado."));
        if (entradaCliente.getSaida() != null) {
            throw new RuntimeException("O registro não está aberto.");
        }
        if (!entradaCliente.getPlaca().equalsIgnoreCase(placa)) {
            throw new RuntimeException("A placa não corresponde ao código informado.");
        }
        entradaRepository.deleteById(codigo);
    }




}
