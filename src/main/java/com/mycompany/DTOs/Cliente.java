/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;

/**
 *
 * @author jonat
 */
import java.math.BigDecimal;

public class Cliente {

    private String dpiCliente;
    private String nitCliente;
    private String nombreCompleto;
    private String apellidoCompleto;
    private String telefono;
    private Double saldo;
    private String usuario;
    private String contrasenia;
    private String direccion;
    private boolean estado;

    public Cliente() {
    }

    public Cliente(String dpiCliente, String nitCliente, String nombreCompleto, String apellidoCompleto,
                    String telefono, Double saldo, String usuario, String contrasenia) {
        this.dpiCliente = dpiCliente;
        this.nitCliente = nitCliente;
        this.nombreCompleto = nombreCompleto;
        this.apellidoCompleto = apellidoCompleto;
        this.telefono = telefono;
        this.saldo = saldo;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getDpiCliente() {
        return dpiCliente;
    }

    public void setDpiCliente(String dpiCliente) {
        this.dpiCliente = dpiCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getApellidoCompleto() {
        return apellidoCompleto;
    }

    public void setApellidoCompleto(String apellidoCompleto) {
        this.apellidoCompleto = apellidoCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    

}
