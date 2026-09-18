/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.chofer;

import com.mycompany.Cruds.CrudChofer;
import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Chofer;
import com.mycompany.POJOs.Personal;
import com.mycompany.POJOs.Sucursal;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @author jonat
 */
@WebServlet(name = "EditarChofer", urlPatterns = {"/EditarChofer"})
public class EditarChofer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                CrudPersonal crudPersonal = new CrudPersonal();
        CrudChofer crudChofer = new CrudChofer();
        String dpi;

        try {
            if (request.getAttribute("dpiChoferEditar") != null) {
                dpi = (String) request.getAttribute("dpiChoferEditar");
            } else {
                dpi = request.getParameter("dpiChoferEditar");
            }
            
            //se obtiene las 2 partes del wey del chofer
            Optional<Personal> personal = crudPersonal.consultarPorDpi(dpi);
            Optional<Chofer> choferOpt = crudChofer.consultarPorDpi(personal.get());
            
            Chofer chofer = crudChofer.pasarDatos(personal.get(), choferOpt.get());
            request.setAttribute("chofer", chofer);

            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);

            request.getRequestDispatcher("/mvc/adminSucursal/VistaEditarChofer.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/CargaDeChofer").forward(request, response);
        }
    }

}

