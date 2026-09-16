/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

/**
 *
 * @author jonat
 */
public class AsignacionChoferBus {

    private int idAsignacion;
    private String noPlaca;
    private String dpiPersonal;
    private boolean estado;

    public AsignacionChoferBus() {
    }

    public AsignacionChoferBus(int idAsignacion, String noPlaca, String dpiPersonal, boolean estado) {
        this.idAsignacion = idAsignacion;
        this.noPlaca = noPlaca;
        this.dpiPersonal = dpiPersonal;
        this.estado = estado;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
