/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.bus;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.Excepciones.RegistroExistenteException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.entidad.Entidad;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * @author jonat
 */
@WebServlet(name = "RegistrarBus", urlPatterns = {"/RegistrarBus"})
public class RegistroBus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroBus.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroBus.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        HttpSession sesion = request.getSession();

        try {
            Bus bus = entidad.crearEntidadBus(request);
            CrudBus crudBus = new CrudBus();
            crudBus.insertarBus(bus);
            sesion.setAttribute("ingresoValido", "El bus se agrego correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeBus");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (RegistroExistenteException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        try {
            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);
        } catch (AccesoDeDatosException ex) {
            // no hay sucursales, valio madre el formulario lo muestra vacio
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/mvc/adminSucursal/VistaRegistroBus.jsp");
        dispatcher.forward(request, response);

    }

}
