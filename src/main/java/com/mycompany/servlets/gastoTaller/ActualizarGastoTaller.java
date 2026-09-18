/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.gastoTaller;

import com.mycompany.Cruds.CrudGastoTaller;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.POJOs.GastoTaller;
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
@WebServlet(name = "ActualizarGastoTaller", urlPatterns = {"/ActualizarGastoTaller"})
public class ActualizarGastoTaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        GastoTaller gasto = null;
        HttpSession sesion = request.getSession();

        try {
            gasto = entidad.crearEntidadGastoTaller(request);
            CrudGastoTaller crudGastoTaller = new CrudGastoTaller();
            crudGastoTaller.actualizarGastoTaller(gasto);
            sesion.setAttribute("ingresoValido", "El gasto de taller se actualizo correctamente");
            response.sendRedirect(request.getContextPath() + "/CargaDeGastoTaller");
            return;

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        String idGasto = request.getParameter("idGasto");
        request.setAttribute("idGastoEditar", idGasto);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EditarGastoTaller");
        dispatcher.forward(request, response);

    }

}
