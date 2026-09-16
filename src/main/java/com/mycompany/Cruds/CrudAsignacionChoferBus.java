/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.AsignacionChoferBus;
import com.mycompany.Excepciones.AccesoDeDatosException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author jonat
 */
public class CrudAsignacionChoferBus{

    private final String NOMBRE_ENTIDAD = "asignacion_chofer_bus";

    //sql
    private final String INSERTAR_ASIGNACION = "INSERT INTO " + NOMBRE_ENTIDAD + "(no_placa, dpi_personal) VALUES(?,?)";
    private final String ACTUALIZAR_ASIGNACION = "UPDATE " + NOMBRE_ENTIDAD + " SET no_placa = ?, dpi_personal = ? WHERE id_asignacion = ?";
    private final String ACTUALIZAR_ESTADO_ASIGNACION = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE id_asignacion = ?";

    //sql queries
    private final String CONSULTAR_TODO_ASIGNACION = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_ID = "SELECT * FROM asignacion_chofer_bus WHERE id_asignacion = ?";

    public CrudAsignacionChoferBus() {

    }

    public void insertarAsignacionChoferBus(AsignacionChoferBus asignacion) throws AccesoDeDatosException{

        String sql = INSERTAR_ASIGNACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, asignacion.getNoPlaca());
            insertar.setString(2, asignacion.getDpiPersonal());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar la asignacion Chofer Bus: ", ex);

        }

    }


    public void habilitarDeshabilitar(AsignacionChoferBus asignacion) throws AccesoDeDatosException {
        boolean estadoActual;

        if (asignacion.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }

        String sql = ACTUALIZAR_ESTADO_ASIGNACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setInt(2, asignacion.getIdAsignacion());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado de la Asignacion ", ex);
        }
    }


    public List<AsignacionChoferBus> consultarAsignaciones() throws AccesoDeDatosException {
        List<AsignacionChoferBus> listaAsignaciones = new ArrayList<>();
        String sql = CONSULTAR_TODO_ASIGNACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet asignacionObtenida = consultar.executeQuery();

            while (asignacionObtenida.next()) {
                AsignacionChoferBus nuevaAsignacion = new AsignacionChoferBus();
                nuevaAsignacion.setIdAsignacion(asignacionObtenida.getInt("id_asignacion"));
                nuevaAsignacion.setNoPlaca(asignacionObtenida.getString("no_placa"));
                nuevaAsignacion.setDpiPersonal(asignacionObtenida.getString("dpi_personal"));
                nuevaAsignacion.setEstado(asignacionObtenida.getBoolean("estado"));
                listaAsignaciones.add(nuevaAsignacion);
            }
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todas las asignaciones ", ex);
        }

        return listaAsignaciones;

    }

    public Optional<AsignacionChoferBus> consultarPorId(AsignacionChoferBus asignacion) throws AccesoDeDatosException {

        int id = asignacion.getIdAsignacion();
        String sql = CONSULTAR_POR_ID;
        AsignacionChoferBus posibleAsignacion = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet asignacionObtenida = consultar.executeQuery();

            while (asignacionObtenida.next()) {
                posibleAsignacion = new AsignacionChoferBus();
                posibleAsignacion.setIdAsignacion(asignacionObtenida.getInt("id_asignacion"));
                posibleAsignacion.setNoPlaca(asignacionObtenida.getString("no_placa"));
                posibleAsignacion.setDpiPersonal(asignacionObtenida.getString("dpi_personal"));
                posibleAsignacion.setEstado(asignacionObtenida.getBoolean("estado"));
            }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Asignacion", ex);
        }

        return Optional.ofNullable(posibleAsignacion);
    }
}