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
import java.util.Optional;

/**
 * @author jonat
 */
@WebServlet(name = "DeshabilitarHabilitarBus", urlPatterns = {"/DeshabilitarHabilitarBus"})
public class DeshabilitarHabilitarBus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrudBus crudBus = new CrudBus();
        String noPlaca = request.getParameter("noPlaca");

        try {
            Optional<Bus> bus = crudBus.consultarPorPlaca(noPlaca);
            if (bus.isPresent()) {
                crudBus.habilitarDeshabilitar(bus.get());
            }

            response.sendRedirect(request.getContextPath() + "/CargaDeBus");

        } catch (AccesoDeDatosException ex) {

        }
    }

}
