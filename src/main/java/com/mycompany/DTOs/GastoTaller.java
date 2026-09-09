/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;

/**
 *
 * @author jonat
 */

import java.sql.Date;

public class GastoTaller {

    private int idGasto;
    private String noPlaca;
    private Date fechaGasto;
    private String descripcion;
    private Double montoRepuesto;
    private Double montoManoObra;
    private Double montoTotal;

    public GastoTaller() {
    }

    public GastoTaller(int idGasto, String noPlaca, Date fechaGasto, String descripcion,
                        Double montoRepuesto, Double montoManoObra, Double montoTotal) {
        this.idGasto = idGasto;
        this.noPlaca = noPlaca;
        this.fechaGasto = fechaGasto;
        this.descripcion = descripcion;
        this.montoRepuesto = montoRepuesto;
        this.montoManoObra = montoManoObra;
        this.montoTotal = montoTotal;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMontoRepuesto() {
        return montoRepuesto;
    }

    public void setMontoRepuesto(Double montoRepuesto) {
        this.montoRepuesto = montoRepuesto;
    }

    public Double getMontoManoObra() {
        return montoManoObra;
    }

    public void setMontoManoObra(Double montoManoObra) {
        this.montoManoObra = montoManoObra;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

}
