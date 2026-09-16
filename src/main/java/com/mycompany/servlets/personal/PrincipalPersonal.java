/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.personal;

import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Personal;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CargaDePersonal", urlPatterns = {"/CargaDePersonal"})
public class PrincipalPersonal extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CrudPersonal crudPersonal = new CrudPersonal();
        
        try{
            
            List<Personal> listaPersonal = crudPersonal.consultarPersonal();
            request.setAttribute("listaPersonal", listaPersonal);
            request.getRequestDispatcher("/mvc/adminSistema/VistaPersonal.jsp").forward(request, response);
        
        } catch(AccesoDeDatosException ex){
            
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSistema/VistaPersonal.jsp").forward(request, response);
            
        }       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
