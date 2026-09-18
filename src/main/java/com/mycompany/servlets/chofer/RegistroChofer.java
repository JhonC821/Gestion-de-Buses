/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.chofer;

import com.mycompany.Cruds.CrudChofer;
import com.mycompany.Cruds.CrudPersonal;
import com.mycompany.Cruds.CrudSucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.CampoEnBlancoException;
import com.mycompany.Excepciones.FormatoIncorrectoException;
import com.mycompany.Excepciones.RegistroExistenteException;
import com.mycompany.POJOs.Chofer;
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
import java.util.List;

/**
 *
 * @author jonat
 */
@WebServlet(name = "RegistrarChofer", urlPatterns = {"/RegistrarChofer"})
public class RegistroChofer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroChofer.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/mvc/adminSucursal/VistaRegistroChofer.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Entidad entidad = new Entidad();
        HttpSession sesion = request.getSession();

        try {
            Chofer chofer = entidad.crearEntidadChofer(request);

            if (chofer.getContrasenia().equals(chofer.getConfirmarContrasenia())) {
                CrudPersonal crudPersonal = new CrudPersonal();
                CrudChofer crudChofer = new CrudChofer();

                crudPersonal.insertarPersonal(chofer);
                crudChofer.insertarChofer(chofer);

                sesion.setAttribute("ingresoValido", "El chofer se agrego correctamente");
                response.sendRedirect(request.getContextPath() + "/CargaDeChofer");
                return;
            } else {
                request.setAttribute("error", "Las contraseñas NO coinsiden");
            }

        } catch (CampoEnBlancoException ex) {
            request.setAttribute("error", ex.getMessage());

        } catch (FormatoIncorrectoException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (RegistroExistenteException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
        }

        try {
            CrudSucursal crudSucursal = new CrudSucursal();
            List<Sucursal> listaSucursales = crudSucursal.consultarSucursales();
            request.setAttribute("listaSucursales", listaSucursales);
        } catch (AccesoDeDatosException ex) {
            // no cargo entonctro las sucrusss
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/mvc/adminSucursal/VistaRegistroChofer.jsp");
        dispatcher.forward(request, response);

    }

}
