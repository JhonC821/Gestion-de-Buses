/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.servlets.gastoTaller;

import com.mycompany.Cruds.CrudBus;
import com.mycompany.Cruds.CrudGastoTaller;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.POJOs.Bus;
import com.mycompany.POJOs.GastoTaller;
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
@WebServlet(name = "EditarGastoTaller", urlPatterns = {"/EditarGastoTaller"})
public class EditarGastoTaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrudGastoTaller crudGastoTaller = new CrudGastoTaller();
        String idGastoStr;

        try {
            if (request.getAttribute("idGastoEditar") != null) {
                idGastoStr = (String) request.getAttribute("idGastoEditar");
            } else {
                idGastoStr = request.getParameter("idGastoEditar");
            }

            GastoTaller gastoConsulta = new GastoTaller();
            gastoConsulta.setIdGasto(Integer.parseInt(idGastoStr));

            Optional<GastoTaller> gasto = crudGastoTaller.consultarPorId(gastoConsulta);
            request.setAttribute("gasto", gasto.get());

            CrudBus crudBus = new CrudBus();
            List<Bus> listaBuses = crudBus.consultarBuses();
            request.setAttribute("listaBuses", listaBuses);

            request.getRequestDispatcher("/mvc/adminSucursal/VistaEditarGastoTaller.jsp").forward(request, response);

        } catch (AccesoDeDatosException ex) {
            request.setAttribute("error", ex.getMessage() + " " + ex.getCause().getMessage());
            request.getRequestDispatcher("/CargaDeGastoTaller").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
