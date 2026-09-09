/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;


import com.mycompany.Conexion.Conexion;
import com.mycompany.DTOs.AsignacionAdminSucursal;
import com.mycompany.DTOs.Personal;
import com.mycompany.DTOs.Sucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.RegistroExistenteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jonat
 */
public class CrudAsignacionAdminSucursal {
    
    private final String NOMBRE_ENTIDAD = "asignacion_admin_sucursal";

    //sql
    private final String INSERTAR_ASIGNACION = "INSERT INTO " + NOMBRE_ENTIDAD + "(dpi_admin, codigo_sucursal) VALUES(?,?)";
    private final String ACTUALIZAR_ASIGNACION = "UPDATE " + NOMBRE_ENTIDAD + " SET dpi_admin = ?, codigo_sucursal = ? WHERE id_asignacion = ?";
    private final String ACTUALIZAR_ESTADO_ASIGNACION = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE id_asignacion = ?";

    //sql queries
    private final String CONSULTAR_TODO_ASIGNACION = "SELECT * FROM " + NOMBRE_ENTIDAD + " WHERE estado = TRUE";
    private final String CONSULTAR_POR_ID = "SELECT * FROM asignacion_admin_sucursal WHERE id_asignacion = ?";
    
    private final String CONSULTAR_POR_SUCURSAL = "SELECT * FROM "+ NOMBRE_ENTIDAD +" WHERE codigo_sucursal = ? AND estado = TRUE";
    private final String CONSULTAR_POR_ADMINISTRADOR = "SELECT * FROM "+ NOMBRE_ENTIDAD +" WHERE dpi_admin = ? AND estado = TRUE";

    public CrudAsignacionAdminSucursal() {

    }

    public void insertarAsignacionAdminSucursal(AsignacionAdminSucursal asignacion) throws AccesoDeDatosException, RegistroExistenteException {

        String sql = INSERTAR_ASIGNACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, asignacion.getDpiAdmin());
            insertar.setString(2, asignacion.getCodigoSucursal());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new RegistroExistenteException("Registro ya existente ", ex);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar la asignacion : ", ex);

        }
    }

    public void habilitarDeshabilitar(AsignacionAdminSucursal asignacion) throws AccesoDeDatosException {
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

            throw new AccesoDeDatosException("Error al Deshabilitar/habilitar la Asignacion ", ex);
        }
    }


    public List<AsignacionAdminSucursal> consultarAsignaciones() throws AccesoDeDatosException {
        List<AsignacionAdminSucursal> listaAsignaciones = new ArrayList<>();
        String sql = CONSULTAR_TODO_ASIGNACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet asignacionObtenida = consultar.executeQuery();

            listaAsignaciones = crearAsignacion(asignacionObtenida);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todas las asignaciones ", ex);
        }

        return listaAsignaciones;

    }

    public List<AsignacionAdminSucursal> consultarPorSucursal(Sucursal sucursalConsulta) throws AccesoDeDatosException {
        List<AsignacionAdminSucursal> listaSucursal;
        String sucursal = sucursalConsulta.getCodigoSucursal();
        String sql = CONSULTAR_POR_SUCURSAL;
        

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, sucursal);
            ResultSet asignacionObtenida = consultar.executeQuery();

            listaSucursal = crearAsignacion(asignacionObtenida);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Asignacion por sucursal", ex);
        }

        return listaSucursal;
    }
    
    public List<AsignacionAdminSucursal> consultarPorAdministrador(Personal personal) throws AccesoDeDatosException {
        List<AsignacionAdminSucursal> listaSucursal;
        String administrador = personal.getDpiPersonal();
        String sql = CONSULTAR_POR_ADMINISTRADOR;
        

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, administrador);
            ResultSet asignacionObtenida = consultar.executeQuery();

            listaSucursal = crearAsignacion(asignacionObtenida);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Asignacion por administrador", ex);
        }

        return listaSucursal;
    }
    
    private List<AsignacionAdminSucursal> crearAsignacion(ResultSet asignacionObtenida) throws SQLException{
        
        List<AsignacionAdminSucursal> listaSucursal = new ArrayList<>();
        
            while (asignacionObtenida.next()) {
                AsignacionAdminSucursal nuevaAsignacion = new AsignacionAdminSucursal();
                nuevaAsignacion.setIdAsignacion(asignacionObtenida.getInt("id_asignacion"));
                nuevaAsignacion.setDpiAdmin(asignacionObtenida.getString("dpi_admin"));
                nuevaAsignacion.setCodigoSucursal(asignacionObtenida.getString("codigo_sucursal"));
                nuevaAsignacion.setEstado(asignacionObtenida.getBoolean("estado"));
                listaSucursal.add(nuevaAsignacion);
            }       
        return listaSucursal;
    }
    
    
    
    
    
}
