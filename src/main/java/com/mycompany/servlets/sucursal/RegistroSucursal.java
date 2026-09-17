/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.sucursal;

import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.RegistroExistenteException;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.entidad.Entidad;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author jonat
 */
@WebServlet(name = "RegistrarSucursal", urlPatterns = {"/RegistrarSucursal"})
public class RegistroSucursal extends HttpServlet {

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
        Entidad entidad = new Entidad();
        HttpSession sesion = request.getSession();

        try {
            Sucursal sucursal = entidad.crearEntidadSucursal(request);
            CrudSucursal crudSucursal = new CrudSucursal();
            crudSucursal.insertarSucursal(sucursal);
            sesion.setAttribute("ingresoValido", "La sucursal se agrego correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeSucursal");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (RegistroExistenteException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            //notificar que valio madres
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/mvc/adminSistema/VistaRegistroSucursal.jsp");
        dispatcher.forward(request, response);

    }

}
