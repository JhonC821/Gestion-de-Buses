/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

/**
 *
 * @author jonat
 */
import com.mycompany.Enums.TipoViaje;
import java.sql.Timestamp;

public class Compra {

    private int idCompra;
    private String dpiCliente;
    private TipoViaje tipoViaje;
    private Timestamp fechaHoraCompra;
    private double montoTotal;
    private String estadoCompra;

    public Compra() {
    }

    public Compra(int idCompra, String dpiCliente, TipoViaje tipoViaje, Timestamp fechaHoraCompra,
                   double montoTotal) {
        this.idCompra = idCompra;
        this.dpiCliente = dpiCliente;
        this.tipoViaje = tipoViaje;
        this.fechaHoraCompra = fechaHoraCompra;
        this.montoTotal = montoTotal;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getDpiCliente() {
        return dpiCliente;
    }

    public void setDpiCliente(String dpiCliente) {
        this.dpiCliente = dpiCliente;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public Timestamp getFechaHoraCompra() {
        return fechaHoraCompra;
    }

    public void setFechaHoraCompra(Timestamp fechaHoraCompra) {
        this.fechaHoraCompra = fechaHoraCompra;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

}
