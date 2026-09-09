/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;

/**
 *
 * @author jonat
 */
public class Configuracion {

    private String clave;
    private double valor;


    public Configuracion() {
    }

    public Configuracion(String clave, double valor) {
        this.clave = clave;
        this.valor = valor;

    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}



