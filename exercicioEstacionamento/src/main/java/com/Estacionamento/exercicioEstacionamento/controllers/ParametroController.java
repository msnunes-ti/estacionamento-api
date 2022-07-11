package com.Estacionamento.exercicioEstacionamento.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/parametros")
public class ParametroController {

    @PostMapping
    public void cadastrarParametro() {

    }

}
