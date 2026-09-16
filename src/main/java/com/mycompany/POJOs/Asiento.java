 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.POJOs;

import com.mycompany.Enums.EstadoAsiento;

/**
 *
 * @author jonat
 */
public class Asiento {

    private int idAsiento;
    private String noPlaca;
    private int numeroAsiento;
    private EstadoAsiento estado;

    public Asiento() {
    }

    public Asiento(int idAsiento, String noPlaca, int numeroAsiento) {
        this.idAsiento = idAsiento;
        this.noPlaca = noPlaca;
        this.numeroAsiento = numeroAsiento;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getNoPlaca() {
        return noPlaca;
    }

    public void setNoPlaca(String noPlaca) {
        this.noPlaca = noPlaca;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }

}
