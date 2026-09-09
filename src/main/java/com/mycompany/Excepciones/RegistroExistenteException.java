/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Excepciones;

/**
 *
 * @author jonat
 */
public class RegistroExistenteException extends Exception{
    
    public RegistroExistenteException(String mensaje, Throwable causa){   
        super(mensaje, causa);
    }
    
}
