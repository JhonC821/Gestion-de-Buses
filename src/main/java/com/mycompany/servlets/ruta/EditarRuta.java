/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.ruta;

import com.mycompany.Cruds.CrudRuta;
import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Ruta;
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
 *
 * @author jonat
 */
@WebServlet(name = "EditarRuta", urlPatterns = {"/EditarRuta"})
public class EditarRuta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudRuta crudRuta = new CrudRuta();
        String codigo;

        try {
            if (request.getAttribute("codigoRutaEditar") != null) {
                codigo = (String) request.getAttribute("codigoRutaEditar");
            } else {
                codigo = request.getParameter("codigoRutaEditar");
            }

            Ruta rutaConsulta = new Ruta();
            rutaConsulta.setCodigoRuta(codigo);

            Optional<Ruta> ruta = crudRuta.consultarPorCodigo(rutaConsulta);
            request.setAttribute("ruta", ruta.get());

            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);

            request.getRequestDispatcher("/mvc/adminSucursal/VistaEditarRuta.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/CargaDeRuta").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
