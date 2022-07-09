package com.Estacionamento.exercicioEstacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private static double valorDaHora = 5.5;

    private int totalDeVeiculos;

    private BigDecimal valor;

    public Financeiro() {
    }

    public static double getValorDaHora() {
        return valorDaHora;
    }

    public static void setValorDaHora(double valorDaHora) {
        Financeiro.valorDaHora = valorDaHora;
    }

    public int getTotalDeVeiculos() {
        return totalDeVeiculos;
    }

    public void setTotalDeVeiculos(int totalDeVeiculos) {
        this.totalDeVeiculos = totalDeVeiculos;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
