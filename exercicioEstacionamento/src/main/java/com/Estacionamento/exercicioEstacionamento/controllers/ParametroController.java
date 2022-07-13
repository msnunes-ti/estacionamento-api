package com.Estacionamento.exercicioEstacionamento.controllers;

import com.Estacionamento.exercicioEstacionamento.dto.CadastraParametroDTO;
import com.Estacionamento.exercicioEstacionamento.model.Parametro;
import com.Estacionamento.exercicioEstacionamento.services.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/parametros")
public class ParametroController {

    @Autowired
    private ParametroService parametroService;

    @PostMapping
    public void criaParametro(@RequestBody @Valid CadastraParametroDTO cadastraParametroDTO) {
        parametroService.criaParametro(cadastraParametroDTO);
    }

    @GetMapping
    public List<Parametro> consultaParametro() {
        return parametroService.consultaParametro();
    }

    @GetMapping(path = "/{codigo}")
    public Parametro parametro(@PathVariable Long codigo){
        return parametroService.encontraParametroPeloId(codigo);
    }

    @PutMapping(path = "/{codigo}")
    public void atualizaParametro(@PathVariable Long codigo, @RequestBody @Valid CadastraParametroDTO cadastraParametroDTO) {
        parametroService.atualizaParametro(codigo, cadastraParametroDTO);
    }

}
