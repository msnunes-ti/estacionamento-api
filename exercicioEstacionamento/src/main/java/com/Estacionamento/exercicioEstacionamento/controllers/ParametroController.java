package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.CadastraParametroDTO;
import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/parametros")
public class ParametroController {

    @Autowired
    CadastraParametroDTO cadastraParametroDTO;

    @GetMapping
    public void consultaParametroDTO() {
        cadastraParametroDTO.consultaTodos();
    }

    @GetMapping(path = "/{codigo}")
    public Optional<Parametro> parametro(@PathVariable Long id){
        return cadastraParametroDTO.consultaPorId(id);
    }

    @PostMapping
    public void alteraParametro(Long id, Double valor) {

    }

}
