/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;

/**
 *
 * @author jonat
 */

import com.mycompany.Enums.EstadoOperativoBus;


public class Bus {

    private String noPlaca;
    private String modelo;
    private String marca;
    private String codigoSucursal;
    private String sucursalActual;
    private int anioFabricacion;
    private int capacidad;
    private byte[] foto;
    private Double kilometrajeActual;
    private EstadoOperativoBus estadoOperativo;
    private boolean estado;

    public Bus() {
    }

    public Bus(String noPlaca, String modelo, String marca, String codigoSucursal, String sucursalActual, int anioFabricacion, 
            int capacidad, byte[] foto, Double kilometrajeActual) {
        this.noPlaca = noPlaca;
        this.modelo = modelo;
        this.marca = marca;
        this.codigoSucursal = codigoSucursal;
        this.sucursalActual = sucursalActual;
        this.anioFabricacion = anioFabricacion;
        this.capacidad = capacidad;
        this.foto = foto;
        this.kilometrajeActual = kilometrajeActual;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getSucursalActual() {
        return sucursalActual;
    }

    public void setSucursalActual(String sucursalActual) {
        this.sucursalActual = sucursalActual;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Double getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(Double kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public EstadoOperativoBus getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(EstadoOperativoBus estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
