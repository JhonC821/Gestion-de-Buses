/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.configuracionsistema;

import com.mycompany.Cruds.CrudConfiguracionSistema;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.ConfiguracionSistema;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author jonat
 */
@WebServlet(name = "CargaDeConfiguracionSistema", urlPatterns = {"/CargaDeConfiguracionSistema"})
public class PrincipalConfiguracionSistema extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudConfiguracionSistema crudConfiguracion = new CrudConfiguracionSistema();

        try {

            List<ConfiguracionSistema> listaConfiguraciones = crudConfiguracion.consultarTodasConfiguraciones();
            request.setAttribute("listaConfiguraciones", listaConfiguraciones);
            request.getRequestDispatcher("/mvc/adminSistema/VistaConfiguracionSistema.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {

            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSistema/VistaConfiguracionSistema.jsp").forward(request, response);

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
