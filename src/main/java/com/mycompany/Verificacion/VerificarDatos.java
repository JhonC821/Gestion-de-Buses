/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Verificacion;


import com.mycompany.Excepciones.FormatoIncorrectoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 *
 * @author jonat
 */
public class VerificarDatos {
    
    public boolean verificarDpi(String dpi) throws FormatoIncorrectoException{
       try{
           
           Long dpiValido = Long.valueOf(dpi);
           if (dpi.length()!=13) {
               throw new NumberFormatException("Tamaño incorrecto de Dpi");
           }
           
            if (dpiValido < 0) {
               throw new NumberFormatException("Número negativo no aceptado");
           }
       
       } catch(NumberFormatException ex) {
  
           throw new FormatoIncorrectoException("Dpi no valido ", ex);
           
       }
       
       return true;
    }
    
        public boolean verificarNIT(String nit) throws FormatoIncorrectoException{
       try{
           
           Long dpiValido = Long.valueOf(nit);
           if (nit.length()!=8) {
               throw new NumberFormatException("Tamaño incorrecto de NIT");
           }
           
            if (dpiValido < 0) {
               throw new NumberFormatException("Número negativo no aceptado");
           }
       
       } catch(NumberFormatException ex) {
  
           throw new FormatoIncorrectoException("NIT no valido ", ex);
           
       }
       
       return true;
    }
    
    public boolean verificarNumeroEntero(String numero) throws FormatoIncorrectoException{
        try{
           
           int numeroValido = Integer.valueOf(numero);
           
            if (numeroValido < 0 ) {
                
                throw new NumberFormatException("Número negativo no aceptado");
            }

        } catch(NumberFormatException ex) {         
           throw new FormatoIncorrectoException("Número entero no valido ", ex);
       } 
        
       return true;
    }
    
    public double verificarNumeroDouble(String numero) throws FormatoIncorrectoException{
        double numeroValido;
        try{
            numeroValido = Double.valueOf(numero);
            
            if (numeroValido < 0) {
               throw new NumberFormatException("Número negativo no aceptado");
            }
        
        }catch(NumberFormatException ex){
            throw new FormatoIncorrectoException("Número decimal no valido", ex);
        }
        
        return numeroValido;
    }
    
    public boolean campoVacio(String campo){
        if (campo == null || campo.trim().isEmpty()) {
            return true;
        }
        
        return false;
    }
    
    public LocalDateTime validarFechaHora(String fecha, String hora) throws FormatoIncorrectoException{
        LocalDate fechaValida;
        LocalTime horaValida;
        LocalDateTime fechaHora;
        
        try {
            fechaValida = LocalDate.parse(fecha);
            horaValida = LocalTime.parse(hora);
            fechaHora = LocalDateTime.of(fechaValida, horaValida);
            
        } catch (DateTimeParseException ex) {
            
            throw new FormatoIncorrectoException("Fecha no Valida ", ex);
        }

        return fechaHora;
    }
    
    public boolean validarFechaHoraNoAnterior(LocalDateTime fechaHora){
        
        if (fechaHora.isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;   
    }
    
    public boolean validarHoraPosteriorAlaSalida(String salida, String llegada) throws FormatoIncorrectoException{
        LocalTime horaSalida;
        LocalTime horaLlegada;
        
        try {
            horaSalida = LocalTime.parse(salida);
            horaLlegada = LocalTime.parse(llegada);
            
            if (horaSalida.isAfter(horaLlegada)) {
                return false;
            }
        } catch (DateTimeParseException e) {
            
            throw new FormatoIncorrectoException("Hora no valida", e);
        }
        
        return true;
    }
    
    public LocalDate validarFecha(String fecha) throws FormatoIncorrectoException{
      LocalDate fechaValida;
      
        try {
            fechaValida = LocalDate.parse(fecha);
             
        } catch (DateTimeParseException ex) {
            throw new FormatoIncorrectoException("Formato de fecha incorrecto ", ex);
        }
       
        return fechaValida;
    }
    
    public boolean validarFechaNoAnterior(LocalDate fecha){
        if (fecha.isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }
    
         
}
