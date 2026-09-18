/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.viaje;

import com.mycompany.Cruds.CrudViaje;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
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

/**
 * @author jonat
 */
@WebServlet(name = "ActualizarViaje", urlPatterns = {"/ActualizarViaje"})
public class ActualizarViaje extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        Viaje viaje = null;
        HttpSession sesion = request.getSession();

        try {
            viaje = entidad.crearEntidadViaje(request);
            CrudViaje crudViaje = new CrudViaje();
            crudViaje.actualizarViaje(viaje);
            sesion.setAttribute("ingresoValido", "El viaje se actualizo correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeViaje");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        String idViaje = request.getParameter("idViaje");
        request.setAttribute("idViajeEditar", idViaje);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EditarViaje");
        dispatcher.forward(request, response);

    }

}
