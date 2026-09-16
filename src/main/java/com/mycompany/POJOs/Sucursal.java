/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

/**
 *
 * @author jonat
 */
public class Sucursal {
 
    private String codigoSucursal;
    private String nombreSucursal;
    private String direccion;
    private boolean estado;
 
    public Sucursal() {
    }
 
    public Sucursal(String codigoSucursal, String nombreSucursal, String direccion) {
        this.codigoSucursal = codigoSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;

    }
 
    public String getCodigoSucursal() {
        return codigoSucursal;
    }
 
    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }
 
    public String getNombreSucursal() {
        return nombreSucursal;
    }
 
    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
 
    public String getDireccion() {
        return direccion;
    }
 
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
 
    public boolean isEstado() {
        return estado;
    }
 
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
 
}
