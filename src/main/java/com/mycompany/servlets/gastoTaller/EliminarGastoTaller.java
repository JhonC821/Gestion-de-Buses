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

/**
 * @author jonat
 */
@WebServlet(name = "EliminarGastoTaller", urlPatterns = {"/EliminarGastoTaller"})
public class EliminarGastoTaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrudGastoTaller crudGastoTaller = new CrudGastoTaller();
        String idGasto = request.getParameter("idGasto");

        try {
            GastoTaller gastoConsulta = new GastoTaller();
            gastoConsulta.setIdGasto(Integer.parseInt(idGasto));

            crudGastoTaller.eliminarGastoTaller(gastoConsulta);
            response.sendRedirect(request.getContextPath() + "/CargaDeGastoTaller");

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/CargaDeGastoTaller").forward(request, response);
        }
    }

}
