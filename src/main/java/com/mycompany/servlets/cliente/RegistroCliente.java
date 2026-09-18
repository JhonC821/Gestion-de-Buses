/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.cliente;

import com.mycompany.Cruds.CrudCliente;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.Excepciones.RegistroExistenteException;
import com.mycompany.POJOs.Cliente;
import com.mycompany.entidad.Entidad;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author jonat
 */
@WebServlet(name = "RegistroCliente", urlPatterns = {"/RegistroCliente"})
public class RegistroCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        
        
        try {
            Cliente cliente = entidad.crearEntidadCliente(request);
            if (cliente.getContrasenia().equals(cliente.getConfirmarContrasenia())) {
                CrudCliente crudCliente = new CrudCliente();
                crudCliente.insertarCliente(cliente);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
                return;
            } else{
            
                request.setAttribute("error","Las contraseñas NO coinsiden");
            }
            
        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());
        }catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }catch (RegistroExistenteException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }
       
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/VistaRegistroCliente.jsp");
        dispatcher.forward(request, response);
        
        
    }

}
