/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.viaje;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Cruds.CrudChofer;
import com.mycompany.Cruds.CrudRuta;
import com.mycompany.Cruds.CrudViaje;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.Chofer;
import com.mycompany.POJOs.Ruta;
import com.mycompany.POJOs.Viaje;
import com.mycompany.entidad.Entidad;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * @author jonat
 */
@WebServlet(name = "RegistrarViaje", urlPatterns = {"/RegistrarViaje"})
public class RegistroViaje extends HttpServlet {

    private void cargarCombos(HttpServletRequest request) throws AccesoDeDatosException {
        CrudBus crudBus = new CrudBus();
        CrudChofer crudChofer = new CrudChofer();
        CrudRuta crudRuta = new CrudRuta();

        List<Bus> listaBuses = crudBus.consultarBuses();
        List<Chofer> listaChoferes = crudChofer.consultarChoferes();
        List<Ruta> listaRutas = crudRuta.consultarRutas();

        request.setAttribute("listaBuses", listaBuses);
        request.setAttribute("listaChoferes", listaChoferes);
        request.setAttribute("listaRutas", listaRutas);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            cargarCombos(request);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroViaje.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroViaje.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        HttpSession sesion = request.getSession();

        try {
            Viaje viaje = entidad.crearEntidadViaje(request);
            CrudViaje crudViaje = new CrudViaje();
            crudViaje.insertarViaje(viaje);
            sesion.setAttribute("ingresoValido", "El viaje se agrego correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeViaje");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        try {
            cargarCombos(request);
        } catch (AccesoDeDatosException ex) {
            // no muestra los atributos pa crear el viajecito
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/mvc/adminSucursal/VistaRegistroViaje.jsp");
        dispatcher.forward(request, response);

    }

}
