package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.CadastraParametroDTO;
import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/parametros")
public class ParametroController {

    CadastraParametroDTO cadastraParametroDTO;

    @PostMapping
    public void criaParametro(Double valor) {
        List<Parametro> parametros = (List<Parametro>) cadastraParametroDTO.consultaTodos();
        if (parametros.size() == 0) {
            cadastraParametroDTO.criaParametro(BigDecimal.valueOf(valor));
        }
        throw new RuntimeException("O Parametro já está criado então, somento pode ser alterado!");
    }

    @GetMapping
    public Iterable<Parametro> consultaParametroDTO() {
        List<Parametro> parametros = (List<Parametro>) cadastraParametroDTO.consultaTodos();
        if (parametros.size() == 0) {
            throw new RuntimeException("Não foram encontrados parametros cadastrados.");
        }
        return parametros;
    }

    @GetMapping(path = "/{codigo}")
    public Optional<Parametro> parametro(@PathVariable Long id){
        return cadastraParametroDTO.consultaPorId(id);
    }



}
