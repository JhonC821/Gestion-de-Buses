/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.bus;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Bus;
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
import java.util.Optional;

/**
 * @author jonat
 */
@WebServlet(name = "ActualizarBus", urlPatterns = {"/ActualizarBus"})
public class ActualizarBus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        Bus bus = null;
        HttpSession sesion = request.getSession();
        CrudBus crudBus = new CrudBus();

        try {
            bus = entidad.crearEntidadBus(request);
            crudBus.actualizarBus(bus);
            sesion.setAttribute("ingresoValido", "El bus se actualizo correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeBus");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        String noPlaca = request.getParameter("noPlaca");
        request.setAttribute("noPlacaEditar", noPlaca);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EditarBus");
        dispatcher.forward(request, response);

    }

}
