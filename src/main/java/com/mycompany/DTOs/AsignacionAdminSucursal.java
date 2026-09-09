/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTOs;

/**
 *
 * @author jonat
 */
public class AsignacionAdminSucursal {
 
    private int idAsignacion;
    private String dpiAdmin;
    private String codigoSucursal;
    private boolean estado;
 
    public AsignacionAdminSucursal() {
    }
 
    public AsignacionAdminSucursal(int idAsignacion, String dpiAdmin, String codigoSucursal, boolean estado) {
        this.idAsignacion = idAsignacion;
        this.dpiAdmin = dpiAdmin;
        this.codigoSucursal = codigoSucursal;
        this.estado = estado;
    }
 
    public int getIdAsignacion() {
        return idAsignacion;
    }
 
    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }
 
    public String getDpiAdmin() {
        return dpiAdmin;
    }
 
    public void setDpiAdmin(String dpiAdmin) {
        this.dpiAdmin = dpiAdmin;
    }
 
    public String getCodigoSucursal() {
        return codigoSucursal;
    }
 
    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }
 
    public boolean isEstado() {
        return estado;
    }
 
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
 
}
