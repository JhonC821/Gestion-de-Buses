/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.gastoTaller;

import com.mycompany.Cruds.CrudGastoTaller;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.GastoTaller;
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
@WebServlet(name = "CargaDeGastoTaller", urlPatterns = {"/CargaDeGastoTaller"})
public class PrincipalGastoTaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudGastoTaller crudGastoTaller = new CrudGastoTaller();

        try {
            List<GastoTaller> listaGastos = crudGastoTaller.consultarGastosTaller();
            request.setAttribute("listaGastos", listaGastos);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaGastoTaller.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaGastoTaller.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
