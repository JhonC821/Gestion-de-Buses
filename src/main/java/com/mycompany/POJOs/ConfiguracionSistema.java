/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

/**
 *
 * @author jonat
 */
public class ConfiguracionSistema {

    private String clave;
    private double precioDeDepreciacionDeBus;


    public ConfiguracionSistema() {
    }

    public ConfiguracionSistema(String clave, double valor) {
        this.clave = clave;
        this.precioDeDepreciacionDeBus = valor;

    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public double getValor() {
        return precioDeDepreciacionDeBus;
    }

    public void setValor(double valor) {
        this.precioDeDepreciacionDeBus = valor;
    }
}



