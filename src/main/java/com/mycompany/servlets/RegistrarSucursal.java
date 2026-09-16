/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets;

import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.RegistroExistenteException;
import com.mycompany.Verificacion.VerificarDatos;
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
@WebServlet(name = "RegistrarSucursal", urlPatterns = {"/RegistrarSucursal"})
public class RegistrarSucursal extends HttpServlet {

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
            out.println("<title>Servlet RegistrarSucursal</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrarSucursal at " + request.getContextPath() + "</h1>");
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
        VerificarDatos verificar = new VerificarDatos();
        CrudSucursal crudSucursal = new CrudSucursal();
        
        Sucursal sucursal = new Sucursal();
        
        String codigoSucursal = request.getParameter("codigoSucursal");
        String nombreSucursal = request.getParameter("nombreSucursal");
        String direccion = request.getParameter("direccion");
        
        if (!(verificar.campoVacio(codigoSucursal)) && !(verificar.campoVacio(nombreSucursal)) && !(verificar.campoVacio(direccion))) {
            sucursal.setCodigoSucursal(codigoSucursal);
            sucursal.setNombreSucursal(nombreSucursal);
            sucursal.setDireccion(direccion);
            
            try {
                crudSucursal.insertarSucursal(sucursal);
                System.out.println("sucursal insertada");
                
            } catch (AccesoDeDatosException  e) {
                
                System.out.println("valio queso" + e.getMessage() + " " + e.getCause().getMessage());
            }catch (RegistroExistenteException  e) {
                System.out.println("valio mas queso");
            }
 
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
