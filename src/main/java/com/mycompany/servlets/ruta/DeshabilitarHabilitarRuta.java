/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.ruta;

import com.mycompany.Cruds.CrudRuta;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Ruta;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 *
 * @author jonat
 */
@WebServlet(name = "DeshabilitarHabilitarRuta", urlPatterns = {"/DeshabilitarHabilitarRuta"})
public class DeshabilitarHabilitarRuta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrudRuta crudRuta = new CrudRuta();
        String codigoRuta = request.getParameter("codigoRuta");

        try {
            Ruta rutaConsulta = new Ruta();
            rutaConsulta.setCodigoRuta(codigoRuta);

            Optional<Ruta> ruta = crudRuta.consultarPorCodigo(rutaConsulta);
            if (ruta.isPresent()) {
                crudRuta.habilitarDeshabilitar(ruta.get());
            }

            response.sendRedirect(request.getContextPath() + "/CargaDeRuta");

        } catch (AccesoDeDatosException ex) {

        }
    }

}
