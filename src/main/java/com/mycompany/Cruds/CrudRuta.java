/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.Ruta;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.Excepciones.AccesoDeDatosException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author jonat
 */
public class CrudRuta {

    private final String NOMBRE_ENTIDAD = "ruta";

    //sql
    private final String INSERTAR_RUTA = "INSERT INTO " + NOMBRE_ENTIDAD + "(codigo_ruta, sucursal_origen, sucursal_destino, distancia_km, precio_boleto) VALUES(?,?,?,?)";
    private final String ACTUALIZAR_RUTA = "UPDATE " + NOMBRE_ENTIDAD + " SET sucursal_origen = ?, sucursal_destino = ?, distancia_km = ?, precio_boleto = ? WHERE codigo_ruta = ?";
    private final String ACTUALIZAR_ESTADO_RUTA = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE codigo_ruta = ?";

    //sql queries
    private final String CONSULTAR_TODO_RUTA = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_CODIGO = "SELECT * FROM ruta WHERE codigo_ruta = ?";
    private final String CONSULTAR_POR_SUCURSAL = "SELECT * FROM " +NOMBRE_ENTIDAD+" WHERE sucursal_origen = ?";

    public CrudRuta() {

    }

    public void insertarRuta(Ruta ruta) throws AccesoDeDatosException{

        String sql = INSERTAR_RUTA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, ruta.getSucursalOrigen());
            insertar.setString(2, ruta.getSucursalDestino());
            insertar.setDouble(3, ruta.getDistanciaKm());
            insertar.setDouble(4, ruta.getPrecioBoleto());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        }catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar la ruta: ", ex);

        }

    }

    public void actualizarRuta(Ruta ruta) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_RUTA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, ruta.getSucursalOrigen());
            actualizar.setString(2, ruta.getSucursalDestino());
            actualizar.setDouble(3, ruta.getDistanciaKm());
            actualizar.setDouble(4, ruta.getPrecioBoleto());
            actualizar.setString(5, ruta.getCodigoRuta());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar la ruta", ex);
        }
    }

    public void habilitarDeshabilitar(Ruta ruta) throws AccesoDeDatosException {
        boolean estadoActual;

        if (ruta.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }

        String sql = ACTUALIZAR_ESTADO_RUTA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setString(2, ruta.getCodigoRuta());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado de la Ruta ", ex);
        }
    }


    public List<Ruta> consultarRutas() throws AccesoDeDatosException {
        List<Ruta> listaRutas = new ArrayList<>();
        String sql = CONSULTAR_TODO_RUTA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet rutaObtenida = consultar.executeQuery();
            
            listaRutas = crearRutas(rutaObtenida);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todas las rutas ", ex);
        }

        return listaRutas;

    }

    public Optional<Ruta> consultarPorCodigo(Ruta ruta) throws AccesoDeDatosException {

        String codigoRuta = ruta.getCodigoRuta();
        String sql = CONSULTAR_POR_CODIGO;
        Ruta posibleRuta = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, codigoRuta);
            ResultSet rutaObtenida = consultar.executeQuery();

            while (rutaObtenida.next()) {
                posibleRuta = new Ruta();
                posibleRuta.setCodigoRuta(rutaObtenida.getString("codigo_ruta"));
                posibleRuta.setSucursalOrigen(rutaObtenida.getString("sucursal_origen"));
                posibleRuta.setSucursalDestino(rutaObtenida.getString("sucursal_destino"));
                posibleRuta.setDistanciaKm(rutaObtenida.getDouble("distancia_km"));
                posibleRuta.setPrecioBoleto(rutaObtenida.getDouble("precio_boleto"));
                posibleRuta.setEstado(rutaObtenida.getBoolean("estado"));
            }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Ruta", ex);
        }

        return Optional.ofNullable(posibleRuta);
    }

    
    public List<Ruta> consultarPorSucursal(Sucursal sucursal) throws AccesoDeDatosException {
        List<Ruta> listaRutas = new ArrayList<>();
        String codigoSucursal = sucursal.getCodigoSucursal();
        String sql = CONSULTAR_POR_SUCURSAL;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, codigoSucursal);
            ResultSet rutasObtenidas = consultar.executeQuery();
            
            listaRutas = crearRutas(rutasObtenidas);
            
            
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar las Rutas por Sucursal: ", ex);
        }

        return listaRutas;
    }
    
    public List<Ruta> crearRutas(ResultSet rutaObtenida) throws SQLException{
        
        List<Ruta> listaRutas = new ArrayList<>();
        while (rutaObtenida.next()) {
            Ruta nuevaRuta = new Ruta();
            nuevaRuta.setCodigoRuta(rutaObtenida.getString("codigo_ruta"));
            nuevaRuta.setSucursalOrigen(rutaObtenida.getString("sucursal_origen"));
            nuevaRuta.setSucursalDestino(rutaObtenida.getString("sucursal_destino"));
            nuevaRuta.setDistanciaKm(rutaObtenida.getDouble("distancia_km"));
            nuevaRuta.setPrecioBoleto(rutaObtenida.getDouble("precio_boleto"));
            nuevaRuta.setEstado(rutaObtenida.getBoolean("estado"));
            listaRutas.add(nuevaRuta);
        }        
        
        return listaRutas;
    }
}
