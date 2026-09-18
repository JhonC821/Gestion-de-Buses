/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.chofer;

import com.mycompany.Cruds.CrudChofer;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Chofer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author jonat
 */
@WebServlet(name = "CargaDeChofer", urlPatterns = {"/CargaDeChofer"})
public class PrincipalChofer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudChofer crudChofer = new CrudChofer();

        try {
            List<Chofer> listaChoferes = crudChofer.consultarChoferes();
            request.setAttribute("listaChoferes", listaChoferes);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaChofer.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaChofer.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
