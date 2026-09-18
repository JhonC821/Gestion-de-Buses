/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.ruta;

import com.mycompany.Cruds.CrudRuta;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Ruta;
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
 *
 * @author jonat
 */
@WebServlet(name = "ActualizarRuta", urlPatterns = {"/ActualizarRuta"})
public class ActualizarRuta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        Ruta ruta = null;
        HttpSession sesion = request.getSession();

        try {
            ruta = entidad.crearEntidadRuta(request);
            CrudRuta crudRuta = new CrudRuta();
            crudRuta.actualizarRuta(ruta);
            sesion.setAttribute("ingresoValido", "La ruta se actualizo correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeRuta");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        String codigoRuta = request.getParameter("codigoRuta");
        request.setAttribute("codigoRutaEditar", codigoRuta);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EditarRuta");
        dispatcher.forward(request, response);

    }

}
