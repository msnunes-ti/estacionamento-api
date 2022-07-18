package com.Estacionamento.exercicioEstacionamento.services;

import com.Estacionamento.exercicioEstacionamento.dto.CadastraParametroDTO;
import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import com.Estacionamento.exercicioEstacionamento.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametroService {

    @Autowired
    private ParametroRepository parametroRepository;

    public void criaParametro(CadastraParametroDTO cadastraParametroDTO) {
        long qtde = parametroRepository.count();
        if (qtde != 0) {
            throw new RuntimeException("O parametro já foi cadastrado.");
        }
        Parametro parametro = new Parametro();
        parametro.setValorHora(cadastraParametroDTO.getValorHora());
        parametro.setHoraInicio(cadastraParametroDTO.getHoraInicio());
        parametro.setHoraFim(cadastraParametroDTO.getHoraFim());
        parametro.setTolerancia(cadastraParametroDTO.getTolerancia());
        parametroRepository.save(parametro);
    }

    public Parametro consultaParametro() {
        return parametroRepository.findFirstByOrderById();
    }

    public Parametro encontraParametroPeloId(Long codigo){
        Optional<Parametro> parametro = parametroRepository.findById(codigo);
        if (parametro.isEmpty()) {
            throw new RuntimeException("Nenhum parametro encontrado.");
        }
        return parametro.get();
    }

    public void atualizaParametro(Long codigo, CadastraParametroDTO cadastraParametroDTO) {
        Optional<Parametro> parametro = parametroRepository.findById(codigo);
        if (parametro.isEmpty()) {
            throw new RuntimeException("Nenhum parametro encontrado.");
        }
        parametro.get().setValorHora(cadastraParametroDTO.getValorHora());
        parametro.get().setHoraInicio((cadastraParametroDTO.getHoraInicio()));
        parametro.get().setHoraFim(cadastraParametroDTO.getHoraFim());
        parametro.get().setTolerancia(cadastraParametroDTO.getTolerancia());
        parametroRepository.save(parametro.get());
    }


}
