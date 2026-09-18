/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.configuracionsistema;

import com.mycompany.Cruds.CrudConfiguracionSistema;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.ConfiguracionSistema;
import com.mycompany.Verificacion.VerificarDatos;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;


@WebServlet(name = "EditarConfiguracionSistema", urlPatterns = {"/EditarConfiguracionSistema"})
public class EditarConfiguracionSistema extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CrudConfiguracionSistema crudConfiguracion = new CrudConfiguracionSistema();
        String clave;
        try {
            
            if (request.getAttribute("clave") != null)
                clave =(String) request.getAttribute("clave");
            else{
                clave = request.getParameter("clave");
            }

            ConfiguracionSistema configuracionConsulta = new ConfiguracionSistema();
            configuracionConsulta.setClave(clave);
            
            //trae la prra configuracion
            Optional<ConfiguracionSistema> configuracion = crudConfiguracion.consultarPorClave(configuracionConsulta);
            request.setAttribute("configuracion", configuracion.get());
            request.getRequestDispatcher("/mvc/adminSistema/VistaEditarConfiguracionSistema.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {

            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSistema/VistaEditarConfiguracionSistema.jsp").forward(request, response);

        }
    }

}
