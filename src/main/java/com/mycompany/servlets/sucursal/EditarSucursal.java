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
@WebServlet(name = "EditarSucursal", urlPatterns = {"/EditarSucursal"})
public class EditarSucursal extends HttpServlet {

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
        String codigo;
        //para verificar si viene de un formulario o otro servlet lo mando al prro
        try {
            if (request.getAttribute("codigoSucursalEditar") != null) {
                codigo = (String) request.getAttribute("codigoSucursalEditar");
            } else {
                codigo = request.getParameter("codigoSucursalEditar");
            }

            Sucursal sucursalConsulta = new Sucursal();
            sucursalConsulta.setCodigoSucursal(codigo);

            Optional<Sucursal> sucursal = crudSucursal.consultarPorCodigo(sucursalConsulta);
            request.setAttribute("sucursal", sucursal.get());
            request.getRequestDispatcher("/mvc/adminSistema/VistaEditarSucursal.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {

        }
    }

}
