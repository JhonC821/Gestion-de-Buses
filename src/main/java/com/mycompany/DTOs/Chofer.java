/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;


import com.mycompany.Enums.EstadoOperativoChofer;
import java.time.LocalDate;

/**
 *
 * @author jonat
 */
public class Chofer extends Personal{
    private String noLicencia;
    private byte[] foto;
    private String tipoLicencia;
    private LocalDate fechaVencimiento;
    private String sucursalAsignada;
    private double salarioBase;
    private EstadoOperativoChofer estadoOperativo;
    private int contadorViajesPrivados;

    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getSucursalAsignada() {
        return sucursalAsignada;
    }

    public void setSucursalAsignada(String sucursalAsignada) {
        this.sucursalAsignada = sucursalAsignada;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public EstadoOperativoChofer getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(EstadoOperativoChofer estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }
    
    
    
}
