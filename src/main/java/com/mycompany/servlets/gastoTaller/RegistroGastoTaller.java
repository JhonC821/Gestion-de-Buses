/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.gastoTaller;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Cruds.CrudGastoTaller;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.GastoTaller;
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
@WebServlet(name = "RegistrarGastoTaller", urlPatterns = {"/RegistrarGastoTaller"})
public class RegistroGastoTaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CrudBus crudBus = new CrudBus();
            List<Bus> listaBuses = crudBus.consultarBuses();
            request.setAttribute("listaBuses", listaBuses);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroGastoTaller.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroGastoTaller.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        HttpSession sesion = request.getSession();

        try {
            GastoTaller gasto = entidad.crearEntidadGastoTaller(request);
            CrudGastoTaller crudGastoTaller = new CrudGastoTaller();
            crudGastoTaller.insertarGastoTaller(gasto);
            sesion.setAttribute("ingresoValido", "El gasto de taller se agrego correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeGastoTaller");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        try {
            CrudBus crudBus = new CrudBus();
            List<Bus> listaBuses = crudBus.consultarBuses();
            request.setAttribute("listaBuses", listaBuses);
        } catch (AccesoDeDatosException ex) {
            //si no se ecuentran los buses pos valio, no muestra nada
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/mvc/Sucursal/VistaRegistroGastoTaller.jsp");
        dispatcher.forward(request, response);

    }

}
