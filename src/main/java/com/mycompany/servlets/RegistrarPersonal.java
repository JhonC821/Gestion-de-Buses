/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets;

import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.DTOs.Personal;
import com.mycompany.Enums.CargoPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.RegistroExistenteException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author jonat
 */
@WebServlet(name = "RegistrarPersonal", urlPatterns = {"/RegistrarPersonal"})
public class RegistrarPersonal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrarPersonal</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarPersonal at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        String dpi = request.getParameter("dpi");
        String nombre = request.getParameter("nombres");
        String apellido = request.getParameter("apellidos");
        
        String telefono = request.getParameter("telefono");
        String cargo = request.getParameter("cargo");
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        
        Personal nuevoPersonal = new Personal();
        nuevoPersonal.setDpiPersonal(dpi);
        nuevoPersonal.setNombreCompleto(nombre);
        nuevoPersonal.setApellidoCompleto(apellido);
        nuevoPersonal.setTelefono(telefono);
        nuevoPersonal.setCargo(CargoPersonal.valueOf(cargo));
        nuevoPersonal.setUsuario(usuario);
        nuevoPersonal.setContrasenia(contrasenia);
        
        CrudPersonal crudPersonal = new CrudPersonal();
        
        try{
            crudPersonal.insertarPersonal(nuevoPersonal);
            
            System.out.println("se inserto correctamente el personal");
        }catch(AccesoDeDatosException ex){
            //notificar que valio madres
            System.out.println("no se inserto esa mamada ");
        
        }catch(RegistroExistenteException ex){
            //notificar que ya hay un pendejo con ese dpi
            System.out.println("El dpi ya existe we" + ex.getMessage() +" " + ex.getCause().getMessage());
        
        } 
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
