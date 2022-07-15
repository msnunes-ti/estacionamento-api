package com.Estacionamento.exercicioEstacionamento.services;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.repository.EntradaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum.TODOS;

@Service
public class EntradaClienteService {

    @Autowired
    private EntradaRepository entradaRepository;


    public EntradaCliente novaEntrada(@Valid EntradaCliente entradaCliente) {
        entradaCliente.setEntrada(LocalDateTime.now());
        entradaRepository.save(entradaCliente);
        return entradaCliente;
    }

    public List<EntradaCliente> obterTodos(SituacaoEnum situacao) {
        return switch (Optional.ofNullable(situacao).orElse(TODOS)) {
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

    public List<EntradaCliente> obterFinalizados() {
        List<EntradaCliente> todos = (List<EntradaCliente>) entradaRepository.findAll();
        List<EntradaCliente> finalizados = new ArrayList<>();
        for (EntradaCliente e : todos) {
            if (e.getSaida() != null) {
                finalizados.add(e);
            }
        }
        return finalizados;
    }

    public List<EntradaCliente> obterPelaPlaca(String placa) {
        return entradaRepository.findByPlacaContainingIgnoreCase(placa);
    }

    public List<EntradaCliente> obterPorDatas(@NotNull LocalDate dataInicial, @NotNull LocalDate dataFinal){
        LocalDateTime inicial = dataInicial.atStartOfDay();
        LocalDateTime saida = dataFinal.atTime(23,59,59,59);
        return entradaRepository.findByEntradaIsBetweenAndSaidaIsNull(inicial, saida);

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
            throw new RuntimeException("Não foi encontrado nenhum registro aberto desse veículo.");
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
        if (entradaRepository.countByPlacaIgnoreCaseAndSaidaIsNull(placa) > 1) {
            throw new RuntimeException("Foram encontradas mais de uma entrada em aberto para essa placa");
        }
        List<EntradaCliente> entradaClientes = entradaRepository.findByPlacaIgnoreCaseAndSaidaIsNull(placa);
        Long id = entradaClientes.get(0).getId();
        deletarRegistroPeloId(id, placa);
    }

    public void deletarRegistroPeloId(@PathVariable Long codigo, @PathVariable String placa) {
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
