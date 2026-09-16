/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

/**
 *
 * @author jonat
 */

import com.mycompany.Enums.EstadoAlquilerDeBus;
import java.sql.Timestamp;

public class AlquilerBus {

    private int idAlquiler;
    private int idCompra;
    private int idViaje;
    private String origen;
    private String destino;
    private double kilometros;
    private Timestamp fechaHoraSalida;
    private EstadoAlquilerDeBus estado;

    public AlquilerBus() {
    }

    public AlquilerBus(int idAlquiler, int idCompra, int idViaje, String origen, String destino,
                        double kilometros, Timestamp fechaHoraSalida) {
        this.idAlquiler = idAlquiler;
        this.idCompra = idCompra;
        this.idViaje = idViaje;
        this.origen = origen;
        this.destino = destino;
        this.kilometros = kilometros;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public Timestamp getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Timestamp fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public EstadoAlquilerDeBus getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlquilerDeBus estado) {
        this.estado = estado;
    }

}
