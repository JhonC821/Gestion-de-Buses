/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.chofer;

import com.mycompany.Cruds.CrudChofer;
import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.Chofer;
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
 * @author jonat
 */
@WebServlet(name = "ActualizarChofer", urlPatterns = {"/ActualizarChofer"})
public class ActualizarChofer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        Chofer chofer = null;
        HttpSession sesion = request.getSession();

        try {
            chofer = entidad.crearEntidadChofer(request);

            if (chofer.getContrasenia().equals(chofer.getConfirmarContrasenia())) {
                CrudPersonal crudPersonal = new CrudPersonal();
                CrudChofer crudChofer = new CrudChofer();

                crudPersonal.actualizarPersonal(chofer);
                crudChofer.actualizarChofer(chofer);

                sesion.setAttribute("ingresoValido", "El chofer se actualizo correctamente");
                response.sendRedirect(request.getContextPath() + "/CargaDeChofer");
                return;
            } else {
                request.setAttribute("error", "Las contraseñas NO coinsiden");
            }

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        String dpiChofer = request.getParameter("dpi");
        request.setAttribute("dpiChoferEditar", dpiChofer);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EditarChofer");
        dispatcher.forward(request, response);

    }

}
