/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;

/**
 *
 * @author jonat
 */


import com.mycompany.Enums.EstadoViaje;
import java.sql.Timestamp;

public class Viaje {

    private int idViaje;
    private String noPlaca;
    private String dpiPersonal;
    private String codigoRuta;
    private Timestamp fechaHoraSalida;
    private Timestamp fechaHoraLlegada;
    private Double kilometrajeInicialBus;
    private Double kilometrajeFinalBus;
    private Double combustibleConsumido;
    private Double depreciacionBus;
    private Double montoTotal;
    private EstadoViaje estado;

    public Viaje() {
    }

    public Viaje(int idViaje, String noPlaca, String dpiPersonal, String codigoRuta, Timestamp fechaHoraSalida,
                  Timestamp fechaHoraLlegada, Double kilometrajeInicialBus, Double kilometrajeFinalBus,
                  Double combustibleConsumido, Double depreciacionBus, Double montoTotal, EstadoViaje estado) {
        this.idViaje = idViaje;
        this.noPlaca = noPlaca;
        this.dpiPersonal = dpiPersonal;
        this.codigoRuta = codigoRuta;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.kilometrajeInicialBus = kilometrajeInicialBus;
        this.kilometrajeFinalBus = kilometrajeFinalBus;
        this.combustibleConsumido = combustibleConsumido;
        this.depreciacionBus = depreciacionBus;
        this.montoTotal = montoTotal;
        this.estado = estado;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    public String getDpiPersonal() {
        return dpiPersonal;
    }

    public void setDpiPersonal(String dpiPersonal) {
        this.dpiPersonal = dpiPersonal;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public Timestamp getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Timestamp fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Timestamp getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(Timestamp fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public Double getKilometrajeInicialBus() {
        return kilometrajeInicialBus;
    }

    public void setKilometrajeInicialBus(Double kilometrajeInicialBus) {
        this.kilometrajeInicialBus = kilometrajeInicialBus;
    }

    public Double getKilometrajeFinalBus() {
        return kilometrajeFinalBus;
    }

    public void setKilometrajeFinalBus(Double kilometrajeFinalBus) {
        this.kilometrajeFinalBus = kilometrajeFinalBus;
    }

    public Double getCombustibleConsumido() {
        return combustibleConsumido;
    }

    public void setCombustibleConsumido(Double combustibleConsumido) {
        this.combustibleConsumido = combustibleConsumido;
    }

    public Double getDepreciacionBus() {
        return depreciacionBus;
    }

    public void setDepreciacionBus(Double depreciacionBus) {
        this.depreciacionBus = depreciacionBus;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public EstadoViaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoViaje estado) {
        this.estado = estado;
    }

}
