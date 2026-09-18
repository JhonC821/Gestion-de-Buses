/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.bus;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Bus;
import java.io.IOException;
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
@WebServlet(name = "CargaDeBus", urlPatterns = {"/CargaDeBus"})
public class PrincipalBus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudBus crudBus = new CrudBus();

        try {
            List<Bus> listaBuses = crudBus.consultarBuses();
            request.setAttribute("listaBuses", listaBuses);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaBus.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaBus.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
