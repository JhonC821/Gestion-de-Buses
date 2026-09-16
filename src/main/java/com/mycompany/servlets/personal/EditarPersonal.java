/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.personal;

import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Personal;
import com.mycompany.Verificacion.VerificarDatos;
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
@WebServlet(name = "EditarPersonal", urlPatterns = {"/EditarPersonal"})
public class EditarPersonal extends HttpServlet {

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
        
        CrudPersonal crudPersonal = new CrudPersonal();
        String dpi;
        
        try{
            if(request.getAttribute("dpiPersonalEditar") != null){
                dpi = (String) request.getAttribute("dpiPersonalEditar");
            } else{
                dpi = request.getParameter("dpiPersonalEditar");
            }
            
            Optional<Personal> personal = crudPersonal.consultarPorDpi(dpi);
            request.setAttribute("personal",personal.get() );
            request.getRequestDispatcher("/mvc/adminSistema/VistaEditarPersonal.jsp").forward(request, response);
                  
        }catch(AccesoDeDatosException ex){
            
            
        }
    }

}
