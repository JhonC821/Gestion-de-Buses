/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.bus;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.Sucursal;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * @author jonat
 */
@WebServlet(name = "EditarBus", urlPatterns = {"/EditarBus"})
public class EditarBus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudBus crudBus = new CrudBus();
        String noPlaca;

        try {
            if (request.getAttribute("noPlacaEditar") != null) {
                noPlaca = (String) request.getAttribute("noPlacaEditar");
            } else {
                noPlaca = request.getParameter("noPlacaEditar");
            }

            Optional<Bus> bus = crudBus.consultarPorPlaca(noPlaca);
            request.setAttribute("bus", bus.get());

            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);

            request.getRequestDispatcher("/mvc/adminSucursal/VistaEditarBus.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/CargaDeBus").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
