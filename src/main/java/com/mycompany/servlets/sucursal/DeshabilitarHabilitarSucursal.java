/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.sucursal;

import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Sucursal;
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
@WebServlet(name = "DeshabilitarHabilitarSucursal", urlPatterns = {"/DeshabilitarHabilitarSucursal"})
public class DeshabilitarHabilitarSucursal extends HttpServlet {

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
        CrudSucursal crudSucursal = new CrudSucursal();
        String codigoSucursal = request.getParameter("codigoSucursal");

        try {
            Sucursal sucursalConsulta = new Sucursal();
            sucursalConsulta.setCodigoSucursal(codigoSucursal);

            Optional<Sucursal> sucursal = crudSucursal.consultarPorCodigo(sucursalConsulta);
            if (sucursal.isPresent()) {
                crudSucursal.habilitarDeshabilitar(sucursal.get());
            }

            response.sendRedirect(request.getContextPath() + "/CargaDeSucursal");

        } catch (AccesoDeDatosException ex) {

        }

    }

}
