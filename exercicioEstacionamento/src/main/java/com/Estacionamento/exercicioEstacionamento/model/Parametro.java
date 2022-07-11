package com.Estacionamento.exercicioEstacionamento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Parametro {

    @Id
    Long id;

    @Column(name = "valor_hora")
    BigDecimal valorHora;
}
