package com.Estacionamento.exercicioEstacionamento.mapper;

import com.Estacionamento.exercicioEstacionamento.dto.EntradaClienteDTO;
import com.Estacionamento.exercicioEstacionamento.model.EntradaCliente;

public class EntradaClienteMapper {

    public static EntradaClienteDTO toEntradaClienteDTO (EntradaCliente entradaCliente) {
        EntradaClienteDTO entradaClienteDTO = new EntradaClienteDTO();
        entradaClienteDTO.setId(entradaCliente.getId());
        entradaClienteDTO.setModelo(entradaCliente.getModelo());
        entradaClienteDTO.setPlaca(entradaCliente.getPlaca());
        entradaClienteDTO.setEntrada(entradaCliente.getEntrada());
        entradaClienteDTO.setSaida((entradaCliente.getSaida()));
        entradaClienteDTO.setValor(entradaCliente.getValor());
        return entradaClienteDTO;
    }
}
