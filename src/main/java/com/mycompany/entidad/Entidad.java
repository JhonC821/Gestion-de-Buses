/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entidad;

import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Enums.CargoPersonal;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Cliente;
import com.mycompany.POJOs.EntidadLogueo;
import com.mycompany.POJOs.Personal;
import com.mycompany.Verificacion.VerificarDatos;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author jonat
 */
public class Entidad {
    
    public Personal crearEntidadPersonal(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String dpi = request.getParameter("dpi");
        String nombre = request.getParameter("nombres");
        String apellido = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String cargo = request.getParameter("cargo");
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        String confirmacionContrasenia = request.getParameter("confirmacionContrasenia");
        
        if (verificar.campoVacio(dpi) || verificar.campoVacio(nombre) || verificar.campoVacio(apellido) ||
            verificar.campoVacio(telefono) || verificar.campoVacio(cargo) || verificar.campoVacio(usuario) || 
            verificar.campoVacio(contrasenia) || verificar.campoVacio(confirmacionContrasenia)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        verificar.verificarDpi(dpi);
        verificar.verificarNumeroEntero(telefono);
        
        Personal nuevoPersonal = new Personal();
        nuevoPersonal.setDpiPersonal(dpi);
        nuevoPersonal.setNombreCompleto(nombre);
        nuevoPersonal.setApellidoCompleto(apellido);
        nuevoPersonal.setTelefono(telefono);
        nuevoPersonal.setCargo(CargoPersonal.valueOf(cargo));
        nuevoPersonal.setUsuario(usuario);
        nuevoPersonal.setContrasenia(contrasenia);
        nuevoPersonal.setConfirmarContrasenia(confirmacionContrasenia);
       
        return nuevoPersonal;
    }
    
    public Cliente crearEntidadCliente(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String dpi = request.getParameter("dpi");
        String nit = request.getParameter("dpi");
        String nombre = request.getParameter("nombres");
        String apellido = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        String confirmacionContrasenia = request.getParameter("confirmacionContrasenia");
        
        if (verificar.campoVacio(dpi) || verificar.campoVacio(nombre) || verificar.campoVacio(apellido) ||
            verificar.campoVacio(telefono) || verificar.campoVacio(nit) || verificar.campoVacio(usuario) || 
            verificar.campoVacio(contrasenia) || verificar.campoVacio(confirmacionContrasenia)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        verificar.verificarDpi(dpi);
        verificar.verificarNIT(nit);
        verificar.verificarNumeroEntero(telefono);
        
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setDpiCliente(dpi);
        nuevoCliente.setNitCliente(nit);
        nuevoCliente.setNombreCompleto(nombre);
        nuevoCliente.setApellidoCompleto(apellido);
        nuevoCliente.setTelefono(telefono);
        nuevoCliente.setUsuario(usuario);
        nuevoCliente.setContrasenia(contrasenia);
        nuevoCliente.setConfirmarContrasenia(confirmacionContrasenia);
        nuevoCliente.setDireccion(direccion);
        
        return nuevoCliente;
    }
    
    public EntidadLogueo crearEntidadDeLogue(HttpServletRequest request) throws CampoEnBlancoException{
        VerificarDatos verificar = new VerificarDatos();
        
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        
        if (verificar.campoVacio(usuario) || verificar.campoVacio(contrasenia)) {
            throw new CampoEnBlancoException("Algunos campos estan Vacios");
            
        }
        
        EntidadLogueo entidad = new EntidadLogueo();
        entidad.setUsuario(usuario);
        entidad.setContrasenia(contrasenia);
        
        return entidad;
    }
    
    
}
