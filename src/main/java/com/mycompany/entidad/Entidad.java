/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entidad;

import com.mycompany.Enums.CargoPersonal;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.Chofer;
import com.mycompany.POJOs.Cliente;
import com.mycompany.POJOs.ConfiguracionSistema;
import com.mycompany.POJOs.EntidadLogueo;
import com.mycompany.POJOs.GastoTaller;
import com.mycompany.POJOs.Personal;
import com.mycompany.POJOs.Ruta;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.POJOs.Viaje;
import com.mycompany.Verificacion.VerificarDatos;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        String nit = request.getParameter("nit");
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
    
    public Sucursal crearEntidadSucursal(HttpServletRequest request) throws CampoEnBlancoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String codigo = request.getParameter("codigoSucursal");
        String nombre = request.getParameter("nombreSucursal");
        String direccion = request.getParameter("direccion");
        
        if (verificar.campoVacio(codigo) || verificar.campoVacio(nombre) || verificar.campoVacio(direccion)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        Sucursal nuevaSucursal = new Sucursal();
        nuevaSucursal.setCodigoSucursal(codigo);
        nuevaSucursal.setNombreSucursal(nombre);
        nuevaSucursal.setDireccion(direccion);
       
        return nuevaSucursal;
    }
    
    public ConfiguracionSistema crearEntidadConfiguracionSistema(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String clave = request.getParameter("clave");
        String valor = request.getParameter("valor");
        
        if (verificar.campoVacio(clave) || verificar.campoVacio(valor)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        double valorValido = verificar.verificarNumeroDouble(valor);
        
        ConfiguracionSistema configuracion = new ConfiguracionSistema();
        configuracion.setClave(clave);
        configuracion.setValor(valorValido);
       
        return configuracion;
    }
    
    public Chofer crearEntidadChofer(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String dpi = request.getParameter("dpi");
        String nombre = request.getParameter("nombres");
        String apellido = request.getParameter("apellidos");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        String confirmacionContrasenia = request.getParameter("confirmacionContrasenia");
        String noLicencia = request.getParameter("noLicencia");
        String tipoLicencia = request.getParameter("tipoLicencia");
        String fechaVencimiento = request.getParameter("fechaVencimiento");
        String sucursalAsignada = request.getParameter("sucursalAsignada");
        String salarioBase = request.getParameter("salarioBase");
        
        if (verificar.campoVacio(dpi) || verificar.campoVacio(nombre) || verificar.campoVacio(apellido) ||
            verificar.campoVacio(telefono) || verificar.campoVacio(usuario) || verificar.campoVacio(contrasenia) ||
            verificar.campoVacio(confirmacionContrasenia) || verificar.campoVacio(noLicencia) ||
            verificar.campoVacio(tipoLicencia) || verificar.campoVacio(fechaVencimiento) ||
            verificar.campoVacio(sucursalAsignada) || verificar.campoVacio(salarioBase)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        verificar.verificarDpi(dpi);
        verificar.verificarNumeroEntero(telefono);
        double salarioValido = verificar.verificarNumeroDouble(salarioBase);
        LocalDate fechaVencimientoValida = verificar.validarFecha(fechaVencimiento);
        
        Chofer nuevoChofer = new Chofer();
        nuevoChofer.setDpiPersonal(dpi);
        nuevoChofer.setNombreCompleto(nombre);
        nuevoChofer.setApellidoCompleto(apellido);
        nuevoChofer.setTelefono(telefono);
        nuevoChofer.setCargo(CargoPersonal.CHOFER);
        nuevoChofer.setUsuario(usuario);
        nuevoChofer.setContrasenia(contrasenia);
        nuevoChofer.setConfirmarContrasenia(confirmacionContrasenia);
        nuevoChofer.setNoLicencia(noLicencia);
        nuevoChofer.setTipoLicencia(tipoLicencia);
        nuevoChofer.setFechaVencimiento(fechaVencimientoValida);
        nuevoChofer.setSucursalAsignada(sucursalAsignada);
        nuevoChofer.setSalarioBase(salarioValido);
       
        return nuevoChofer;
    }
    
    public Ruta crearEntidadRuta(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String codigo = request.getParameter("codigoRuta");
        String sucursalOrigen = request.getParameter("sucursalOrigen");
        String sucursalDestino = request.getParameter("sucursalDestino");
        String distanciaKm = request.getParameter("distanciaKm");
        String precioBoleto = request.getParameter("precioBoleto");
        
        if (verificar.campoVacio(codigo) || verificar.campoVacio(sucursalOrigen) || verificar.campoVacio(sucursalDestino) ||
            verificar.campoVacio(distanciaKm) || verificar.campoVacio(precioBoleto)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
        }
        
        if (sucursalOrigen.equalsIgnoreCase(sucursalDestino)) {
            throw new CampoEnBlancoException("La sucursal de origen y destino no pueden ser la misma");
        }
        
        double distanciaValida = verificar.verificarNumeroDouble(distanciaKm);
        double precioValido = verificar.verificarNumeroDouble(precioBoleto);
        
        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setCodigoRuta(codigo);
        nuevaRuta.setSucursalOrigen(sucursalOrigen);
        nuevaRuta.setSucursalDestino(sucursalDestino);
        nuevaRuta.setDistanciaKm(distanciaValida);
        nuevaRuta.setPrecioBoleto(precioValido);
       
        return nuevaRuta;
    }
    
    public Bus crearEntidadBus(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String noPlaca = request.getParameter("noPlaca");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String codigoSucursal = request.getParameter("codigoSucursal");
        String anioFabricacion = request.getParameter("anioFabricacion");
        String capacidad = request.getParameter("capacidad");
        String kilometrajeActual = request.getParameter("kilometrajeActual");
        
        if (verificar.campoVacio(noPlaca) || verificar.campoVacio(modelo) || verificar.campoVacio(marca) ||
            verificar.campoVacio(codigoSucursal) || verificar.campoVacio(anioFabricacion) || verificar.campoVacio(capacidad) 
            || verificar.campoVacio(kilometrajeActual)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        verificar.verificarNumeroEntero(anioFabricacion);
        verificar.verificarNumeroEntero(capacidad);
        double kilometrajeValido = verificar.verificarNumeroDouble(kilometrajeActual);
        
        Bus nuevoBus = new Bus();
        nuevoBus.setNoPlaca(noPlaca);
        nuevoBus.setModelo(modelo);
        nuevoBus.setMarca(marca);
        nuevoBus.setCodigoSucursal(codigoSucursal);
        nuevoBus.setSucursalActual(codigoSucursal);
        nuevoBus.setAnioFabricacion(Integer.parseInt(anioFabricacion));
        nuevoBus.setCapacidad(Integer.parseInt(capacidad));
        nuevoBus.setKilometrajeActual(kilometrajeValido);
       
        return nuevoBus;
    }
    
    public GastoTaller crearEntidadGastoTaller(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String idGasto = request.getParameter("idGasto");
        String noPlaca = request.getParameter("noPlaca");
        String fechaGasto = request.getParameter("fechaGasto");
        String descripcion = request.getParameter("descripcion");
        String montoRepuesto = request.getParameter("montoRepuesto");
        String montoManoObra = request.getParameter("montoManoObra");
        
        if (verificar.campoVacio(noPlaca) || verificar.campoVacio(fechaGasto) || verificar.campoVacio(descripcion) ||
            verificar.campoVacio(montoRepuesto) || verificar.campoVacio(montoManoObra)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        LocalDate fechaValida = verificar.validarFecha(fechaGasto);
        double montoRepuestoValido = verificar.verificarNumeroDouble(montoRepuesto);
        double montoManoObraValido = verificar.verificarNumeroDouble(montoManoObra);
        
        GastoTaller nuevoGasto = new GastoTaller();
        if (idGasto != null && !idGasto.trim().isEmpty()) {
            nuevoGasto.setIdGasto(Integer.parseInt(idGasto));
        }
        nuevoGasto.setNoPlaca(noPlaca);
        nuevoGasto.setFechaGasto(java.sql.Date.valueOf(fechaValida));
        nuevoGasto.setDescripcion(descripcion);
        nuevoGasto.setMontoRepuesto(montoRepuestoValido);
        nuevoGasto.setMontoManoObra(montoManoObraValido);
        nuevoGasto.setMontoTotal(montoRepuestoValido + montoManoObraValido); //setear el totoal, que no se olvide esa mamada
       
        return nuevoGasto;
    }
    
    public Viaje crearEntidadViaje(HttpServletRequest request) throws CampoEnBlancoException, FormatoIncorrectoException{
        
        VerificarDatos verificar = new VerificarDatos();
        
        String idViaje = request.getParameter("idViaje");
        String noPlaca = request.getParameter("noPlaca");
        String dpiPersonal = request.getParameter("dpiPersonal");
        String codigoRuta = request.getParameter("codigoRuta");
        String fechaSalida = request.getParameter("fechaSalida");
        String horaSalida = request.getParameter("horaSalida");
        String kilometrajeInicialBus = request.getParameter("kilometrajeInicialBus");
        
        if (verificar.campoVacio(noPlaca) || verificar.campoVacio(dpiPersonal) || verificar.campoVacio(codigoRuta) ||
            verificar.campoVacio(fechaSalida) || verificar.campoVacio(horaSalida) || verificar.campoVacio(kilometrajeInicialBus)) {
            
            throw new CampoEnBlancoException("Alguno de los campos esta Vacío");
            
        }
        
        LocalDateTime fechaHoraValida = verificar.validarFechaHora(fechaSalida, horaSalida);
        double kilometrajeValido = verificar.verificarNumeroDouble(kilometrajeInicialBus);
        
        Viaje nuevoViaje = new Viaje();
        if (idViaje != null && !idViaje.trim().isEmpty()) {
            nuevoViaje.setIdViaje(Integer.parseInt(idViaje));
        }
        nuevoViaje.setNoPlaca(noPlaca);
        nuevoViaje.setDpiPersonal(dpiPersonal);
        nuevoViaje.setCodigoRuta(codigoRuta);
        nuevoViaje.setFechaHoraSalida(java.sql.Timestamp.valueOf(fechaHoraValida));
        nuevoViaje.setKilometrajeInicialBus(kilometrajeValido);
       
        return nuevoViaje;
    }
    
}
    

