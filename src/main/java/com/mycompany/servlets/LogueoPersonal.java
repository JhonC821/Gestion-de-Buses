/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets;


import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Enums.CargoPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.POJOs.EntidadLogueo;
import com.mycompany.POJOs.Personal;
import com.mycompany.entidad.Entidad;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

/**
 *
 * @author jonat
 */
@WebServlet(name = "LogueoPersonal", urlPatterns = {"/LogueoPersonal"})
public class LogueoPersonal extends HttpServlet {



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
        Entidad entidad = new Entidad();
        Optional<Personal> personal;
        
        
        try {
            EntidadLogueo personalLogueo = entidad.crearEntidadDeLogue(request);
            personal = crudPersonal.consultarPorUsuarioContrasenia(personalLogueo.getUsuario(), personalLogueo.getContrasenia());
            if(personal.isPresent()){
                // pasarle el objeto a la otra ventana
                HttpSession session = request.getSession(); // se crea si se encontro al man
                session.setAttribute("usuario", personal.get());
                session.setAttribute("cargo", personal.get().getCargo().name());
                
                if (personal.get().getCargo().equals(CargoPersonal.ADMINISTRADOR_SISTEMA)) {
                    //request.getRequestDispatcher("/mvc/adminSistema/VistaPrincipalSistema.jsp").forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/mvc/adminSistema/VistaPrincipalSistema.jsp");
                    return;
                } else if (personal.get().getCargo().equals(CargoPersonal.ADMINISTRADOR_SUCURSAL)){
                    //request.getRequestDispatcher("/mvc/adminSucursal/VistaPrincipalSucursal.jsp").forward(request, response); 
                    response.sendRedirect(request.getContextPath() + "/mvc/adminSucursal/VistaPrincipalSucursal.jsp");
                    return;
                }
                
                
            } else{
               //notificar que valio verga 
               request.setAttribute("error", "Usuario o contraseña incorrecta");
            }
        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());
        }catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }
        
        request.getRequestDispatcher("index-personal.jsp").forward(request, response);
    }
        
}
    
    
    

