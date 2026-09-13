/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
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
import java.util.Optional;

/**
 *
 * @author jonat
 */
public class CrudSucursal {

    private final String NOMBRE_ENTIDAD = "sucursal";

    //sql
    private final String INSERTAR_SUCURSAL = "INSERT INTO " + NOMBRE_ENTIDAD + "(codigo_sucursal, nombre_sucursal, direccion) VALUES(?,?,?)";
    private final String ACTUALIZAR_SUCURSAL = "UPDATE " + NOMBRE_ENTIDAD + " SET nombre_sucursal = ?, direccion = ? WHERE codigo_sucursal = ?";
    private final String ACTUALIZAR_ESTADO_SUCURSAL = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE codigo_sucursal = ?";

    //sql queriesss
    private final String CONSULTAR_TODO_SUCURSAL = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_CODIGO = "SELECT * FROM "+NOMBRE_ENTIDAD + " WHERE codigo_sucursal = ?";


    public void insertarSucursal(Sucursal sucursal) throws AccesoDeDatosException, RegistroExistenteException {

        String sql = INSERTAR_SUCURSAL;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, sucursal.getCodigoSucursal());
            insertar.setString(2, sucursal.getNombreSucursal());
            insertar.setString(3, sucursal.getDireccion());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new RegistroExistenteException("Sucursal ya existente ", ex);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar Sucursal: ", ex);
        }

    }

    public void actualizarSucursal(Sucursal sucursal) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_SUCURSAL;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, sucursal.getNombreSucursal());
            actualizar.setString(2, sucursal.getDireccion());
            actualizar.setString(4, sucursal.getCodigoSucursal());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar la sucursal", ex);
        }
    }

    public void habilitarDeshabilitar(Sucursal sucursal) throws AccesoDeDatosException {
        boolean estadoActual;

        if (sucursal.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }

        String sql = ACTUALIZAR_ESTADO_SUCURSAL;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setString(2, sucursal.getCodigoSucursal());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado de la Sucursal ", ex);
        }
    }


    public List<Sucursal> consultarSucursales() throws AccesoDeDatosException {
        List<Sucursal> listaSucursales = new ArrayList<>();
        String sql = CONSULTAR_TODO_SUCURSAL;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet sucursalObtenida = consultar.executeQuery();

            while (sucursalObtenida.next()) {
                Sucursal nuevaSucursal = new Sucursal();
                nuevaSucursal.setCodigoSucursal(sucursalObtenida.getString("codigo_sucursal"));
                nuevaSucursal.setNombreSucursal(sucursalObtenida.getString("nombre_sucursal"));
                nuevaSucursal.setDireccion(sucursalObtenida.getString("direccion"));
                nuevaSucursal.setEstado(sucursalObtenida.getBoolean("estado"));
                listaSucursales.add(nuevaSucursal);
            }
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todas las sucursales ", ex);
        }

        return listaSucursales;
    }

    public Optional<Sucursal> consultarPorCodigo(Sucursal sucursal) throws AccesoDeDatosException {

        String codigo = sucursal.getCodigoSucursal();
        String sql = CONSULTAR_POR_CODIGO;  
        Sucursal posibleSucursal = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, codigo);
            ResultSet sucursalObtenida = consultar.executeQuery();

            while (sucursalObtenida.next()) {
                posibleSucursal = new Sucursal();
                posibleSucursal.setCodigoSucursal(sucursalObtenida.getString("codigo_sucursal"));
                posibleSucursal.setNombreSucursal(sucursalObtenida.getString("nombre_sucursal"));
                posibleSucursal.setDireccion(sucursalObtenida.getString("direccion"));
                posibleSucursal.setEstado(sucursalObtenida.getBoolean("estado"));
            }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Sucursal", ex);
        }

        return Optional.ofNullable(posibleSucursal);
    }
}
