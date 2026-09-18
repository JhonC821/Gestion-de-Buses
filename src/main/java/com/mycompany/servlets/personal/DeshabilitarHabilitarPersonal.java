/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.personal;

import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Personal;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeshabilitarHabilitarPersonal", urlPatterns = {"/DeshabilitarHabilitarPersonal"})
public class DeshabilitarHabilitarPersonal extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CrudPersonal crudPersonal = new CrudPersonal();
        String dpiPersonal = request.getParameter("dpiPersonal");
        
        try{
            Optional<Personal> personal = crudPersonal.consultarPorDpi(dpiPersonal);
            if (personal.isPresent()) {
                    crudPersonal.habilitarDeshabilitar(personal.get());
            }
            
            response.sendRedirect(request.getContextPath() +"/CargaDePersonal");
   
        } catch(AccesoDeDatosException ex){
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/CargaDePersonal");
            dispatcher.forward(request, response);
        }
        
    }

}
