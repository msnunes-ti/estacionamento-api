package com.Estacionamento.exercicioEstacionamento.services;

import com.Estacionamento.exercicioEstacionamento.dto.AlteraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.CadastraEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.EntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.dto.SaidaEntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum;
import com.Estacionamento.exercicioEstacionamento.mapper.EntradaClienteMapper;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;
import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import com.Estacionamento.exercicioEstacionamento.repository.EntradaRepository;
import com.Estacionamento.exercicioEstacionamento.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.Estacionamento.exercicioEstacionamento.enums.SituacaoEnum.TODOS;

@Service
public class EntradaClienteService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ParametroService parametroService;

    @Autowired
    private ParametroRepository parametroRepository;


    public EntradaClienteDTO novaEntrada(CadastraEntradaClienteDTO cadastraEntradaClienteDTO) {
        EntradaCliente entradaCliente = new EntradaCliente();
        entradaCliente.setModelo(cadastraEntradaClienteDTO.getModelo());
        entradaCliente.setPlaca(cadastraEntradaClienteDTO.getPlaca());
        entradaCliente.setEntrada(LocalDateTime.now());
        Parametro parametro = parametroService.consultaParametro();
        if (entradaCliente.getEntrada().toLocalTime().isBefore(parametro.getHoraInicio().toLocalTime())) {
            throw new RuntimeException("A hora de entrada não deve ser anterior ao horário de abertura: " + parametro.getHoraInicio() + ".");
        }
        if (entradaCliente.getEntrada().toLocalTime().isAfter(parametro.getHoraFim().toLocalTime())) {
            throw new RuntimeException("A hora de entrada não deve ser posterior ao horário de fechamento: " + parametro.getHoraFim() + ".");
        }
        entradaRepository.save(entradaCliente);
        return EntradaClienteMapper.toEntradaClienteDTO(entradaCliente);
    }

    public List<EntradaCliente> obterTodos(SituacaoEnum situacao) {
        return switch (Optional.ofNullable(situacao).orElse(TODOS)) {
            case FECHADO -> entradaRepository.findBySaidaNotNull();
            case ABERTO -> entradaRepository.findBySaidaIsNull();
            default -> entradaRepository.findAll();
        };
    }

    public List<EntradaCliente> obterTodosPorIntervadoDeDatas(SituacaoEnum situacao, LocalDate dataInicial, LocalDate dataFinal) {
        LocalDateTime dataInicio = dataInicial.atStartOfDay();
        LocalDateTime dataFim = dataFinal.atTime(LocalTime.MAX);
        if (dataInicio.isAfter(dataFim)) {
            throw new RuntimeException("A data final é menor que a data inicial");
        }
        return switch (Optional.ofNullable(situacao).orElse(TODOS)) {
            case FECHADO -> entradaRepository.findByEntradaIsBetweenAndSaidaNotNull(dataInicio, dataFim);
            case ABERTO -> entradaRepository.findByEntradaIsBetweenAndSaidaIsNull(dataInicio, dataFim);
            default -> entradaRepository.findByEntradaIsBetween(dataInicio, dataFim);
        };
    }

    public List<EntradaCliente> obterPorFiltros(String dataInicial, String dataFinal, SituacaoEnum situacaoEnum){
        if (dataInicial == null || dataFinal == null) {
            return obterTodos(situacaoEnum);
        }
        LocalDate dataInicio = LocalDate.parse(dataInicial);
        LocalDate dataFim = LocalDate.parse(dataFinal);
        return obterTodosPorIntervadoDeDatas(situacaoEnum, dataInicio, dataFim);
    }

    public Iterable<EntradaCliente> obterAbertos() {
        List<EntradaCliente> todos = entradaRepository.findAll();
        List<EntradaCliente> abertos = new ArrayList<>();
        for (EntradaCliente e : todos) {
            if (e.getSaida() == null) {
                abertos.add(e);
            }
        }
        return abertos;
    }

    public List<EntradaCliente> obterFinalizados() {
        List<EntradaCliente> todos = entradaRepository.findAll();
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

    public SaidaEntradaClienteDTO registraSaida(String placa) {
        List<EntradaCliente> entradaClientes = entradaRepository.findByPlacaIgnoreCaseAndSaidaIsNull(placa);
        if (entradaClientes.size() > 1) {
            throw new RuntimeException("Existe mais de um registro aberto para essa placa.");
        }
        if (entradaClientes.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado.");
        }
        Parametro parametro = parametroService.consultaParametro();
//        int tolerancia = parametro.getTolerancia();
        EntradaCliente entradaCliente = entradaClientes.get(0);
        entradaCliente.setSaida(LocalDateTime.now());
        int horas = (int) (1 + entradaCliente.getEntrada().until(entradaCliente.getSaida(), ChronoUnit.HOURS));
        if (entradaCliente.getEntrada().until(entradaCliente.getSaida(), ChronoUnit.MINUTES) <= parametro.getTolerancia()) {
            horas = 0;
        }
//        if (Optional.ofNullable(tolerancia).orElse(0)) {
//
//        }
        BigDecimal valorHora = parametro.getValorHora();
        entradaCliente.setValor(valorHora.multiply((BigDecimal.valueOf(horas))));
        if (entradaCliente.getSaida().toLocalTime().isBefore(parametro.getHoraInicio().toLocalTime())) {
            throw new RuntimeException("A hora de saida não deve ser anterior ao horário de abertura: " + parametro.getHoraInicio() + ".");
        }
        if (entradaCliente.getSaida().toLocalTime().isAfter(parametro.getHoraFim().toLocalTime())) {
            throw new RuntimeException("A hora de saída não deve ser posterior ao horário de fechamento: " + parametro.getHoraFim() + ".");
        }
        entradaRepository.save(entradaCliente);
        SaidaEntradaClienteDTO saidaEntradaClienteDTO = new SaidaEntradaClienteDTO();
        saidaEntradaClienteDTO.setEntradaCliente(entradaCliente);
        saidaEntradaClienteDTO.setValorHora(parametro.getValorHora());
        return saidaEntradaClienteDTO;
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
