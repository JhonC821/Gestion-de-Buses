/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.viaje;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Cruds.CrudChofer;
import com.mycompany.Cruds.CrudRuta;
import com.mycompany.Cruds.CrudViaje;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.Chofer;
import com.mycompany.POJOs.Ruta;
import com.mycompany.POJOs.Viaje;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @author jonat
 */
@WebServlet(name = "EditarViaje", urlPatterns = {"/EditarViaje"})
public class EditarViaje extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudViaje crudViaje = new CrudViaje();
        String idViajeStr;

        try {
            if (request.getAttribute("idViajeEditar") != null) {
                idViajeStr = (String) request.getAttribute("idViajeEditar");
            } else {
                idViajeStr = request.getParameter("idViajeEditar");
            }

            Viaje viajeConsulta = new Viaje();
            viajeConsulta.setIdViaje(Integer.parseInt(idViajeStr));

            Optional<Viaje> viaje = crudViaje.consultarPorId(viajeConsulta);
            request.setAttribute("viaje", viaje.get());

            CrudBus crudBus = new CrudBus();
            CrudChofer crudChofer = new CrudChofer();
            CrudRuta crudRuta = new CrudRuta();

            List<Bus> listaBuses = crudBus.consultarBuses();
            List<Chofer> listaChoferes = crudChofer.consultarChoferes();
            List<Ruta> listaRutas = crudRuta.consultarRutas();

            request.setAttribute("listaBuses", listaBuses);
            request.setAttribute("listaChoferes", listaChoferes);
            request.setAttribute("listaRutas", listaRutas);

            request.getRequestDispatcher("/mvc/adminSucursal/VistaEditarViaje.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/CargaDeViaje").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
