/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.personal;

import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.Excepciones.RegistroExistenteException;
import com.mycompany.POJOs.Personal;
import com.mycompany.entidad.Entidad;
import jakarta.jms.Session;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ActualizarPersonal", urlPatterns = {"/ActualizarPersonal"})
public class ActualizarPersonal extends HttpServlet {


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
        Entidad entidad = new Entidad();
        Personal personal = null;
        HttpSession sesion = request.getSession();
        
        try{
            personal = entidad.crearEntidadPersonal(request);
            
            if (personal.getContrasenia().equals(personal.getConfirmarContrasenia())) {
                CrudPersonal crudPersonal = new CrudPersonal();
                crudPersonal.actualizarPersonal(personal);
                sesion.setAttribute("ingresoValido", "El personal se Actualizo correctamente");
                response.sendRedirect(request.getContextPath() + "/CargaDePersonal");
                //RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/CargaDePersonal");
                //dispatcher.forward(request, response);
                return;
            } else{
                
                request.setAttribute("error","Error en la acutalizacion: Las contraseñas NO coinsiden");
            }
           
        
        } catch(CampoEnBlancoException ex){
            request.setAttribute("error", ex.getMessage());
        
        } catch(FormatoIncorrectoException ex){
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
       
        } catch(AccesoDeDatosException ex){
                    //notificar que valio madres
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        } 
        
        String dpiPersonal = request.getParameter("dpi");
        request.setAttribute("dpiPersonalEditar", dpiPersonal);
        RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/EditarPersonal");
        dispatcher.forward(request, response);

    }

}
