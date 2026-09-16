/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

/**
 *
 * @author jonat
 */
import java.math.BigDecimal;

public class Boleto {

    private int idBoleto;
    private int idCompra;
    private int idViaje;
    private int idAsiento;
    private boolean estado;

    public Boleto() {
    }

    public Boleto(int idBoleto, int idCompra, int idViaje, int idAsiento) {
        this.idBoleto = idBoleto;
        this.idCompra = idCompra;
        this.idViaje = idViaje;
        this.idAsiento = idAsiento;
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}