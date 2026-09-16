/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

import com.mycompany.Enums.CargoPersonal;

/**
 *
 * @author jonat
 */
public class Personal {
    private String dpiPersonal;
    private String nombreCompleto;
    private String apellidoCompleto;
    private String telefono;
    private CargoPersonal cargo;
    private String usuario;
    private String contrasenia;
    private String confirmarContrasenia;
    private boolean estado;
    
    public Personal(){
        
    }

    public String getDpiPersonal() {
        return dpiPersonal;
    }

    public void setDpiPersonal(String dpiPersonal) {
        this.dpiPersonal = dpiPersonal;
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

    public CargoPersonal getCargo() {
        return cargo;
    }

    public void setCargo(CargoPersonal cargo) {
        this.cargo = cargo;
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

    public String getConfirmarContrasenia() {
        return confirmarContrasenia;
    }

    public void setConfirmarContrasenia(String confirmarContrasenia) {
        this.confirmarContrasenia = confirmarContrasenia;
    }
    
    
}
