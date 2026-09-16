/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.Compra;
import com.mycompany.Enums.TipoViaje;
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
public class CrudCompra {

    private final String NOMBRE_ENTIDAD = "compra";

    //sql
    private final String INSERTAR_COMPRA = "INSERT INTO " + NOMBRE_ENTIDAD + "(dpi_cliente, tipo_viaje, fecha_hora_compra, monto_total) VALUES(?,?,?,?)";
    private final String ACTUALIZAR_COMPRA = "UPDATE " + NOMBRE_ENTIDAD + " SET tipo_viaje = ?, fecha_hora_compra = ?, monto_total = ? WHERE id_compra = ?";
    private final String ACTUALIZAR_ESTADO_COMPRA = "UPDATE " + NOMBRE_ENTIDAD + " SET estado_compra = ? WHERE id_compra = ?";

    //sql queries
    private final String CONSULTAR_TODO_COMPRA = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_ID = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE id_compra = ?";
    private final String CONSULTAR_POR_CLIENTE = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE dpi_cliente = ?";

    public CrudCompra() {

    }

    public void insertarCompra(Compra compra) throws AccesoDeDatosException{

        String sql = INSERTAR_COMPRA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, compra.getDpiCliente());
            insertar.setString(2, compra.getTipoViaje().name());
            insertar.setTimestamp(3, compra.getFechaHoraCompra());
            insertar.setDouble(4, compra.getMontoTotal());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        }catch(SQLException ex){
            throw new AccesoDeDatosException("Error al insertar: ", ex);

        }

    }

    public void actualizarCompra(Compra compra) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_COMPRA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, compra.getTipoViaje().name());
            actualizar.setTimestamp(2, compra.getFechaHoraCompra());
            actualizar.setDouble(3, compra.getMontoTotal());
            actualizar.setInt(4, compra.getIdCompra());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar la compra", ex);
        }
    }

    public void actualizarEstadoCompra(Compra compra) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_ESTADO_COMPRA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setString(1, compra.getEstadoCompra());
            actualizarEstado.setInt(2, compra.getIdCompra());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado de la Compra ", ex);
        }
    }


    public List<Compra> consultarCompras() throws AccesoDeDatosException {
        List<Compra> listaCompras = new ArrayList<>();
        String sql = CONSULTAR_TODO_COMPRA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet compraObtenida = consultar.executeQuery();

            listaCompras = crearCompras(compraObtenida);
            
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todas las compras ", ex);
        }

        return listaCompras;

    }

    public List<Compra> consultarPorCliente(Compra compra) throws AccesoDeDatosException {
        List<Compra> listaCompras = new ArrayList<>();
        String sql = CONSULTAR_POR_CLIENTE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, compra.getDpiCliente());
            ResultSet compraObtenida = consultar.executeQuery();

            listaCompras = crearCompras(compraObtenida);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el historial de compras del cliente ", ex);
        }

        return listaCompras;
    }

    public Optional<Compra> consultarPorId(Compra compra) throws AccesoDeDatosException {

        int id = compra.getIdCompra();
        String sql = CONSULTAR_POR_ID;
        Compra posibleCompra = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet compraObtenida = consultar.executeQuery();

            posibleCompra = new Compra();
            posibleCompra.setIdCompra(compraObtenida.getInt("id_compra"));
            posibleCompra.setDpiCliente(compraObtenida.getString("dpi_cliente"));
            posibleCompra.setTipoViaje(TipoViaje.valueOf( compraObtenida.getString("tipo_viaje")));
            posibleCompra.setFechaHoraCompra(compraObtenida.getTimestamp("fecha_hora_compra"));
            posibleCompra.setMontoTotal(compraObtenida.getDouble("monto_total"));
            posibleCompra.setEstadoCompra(compraObtenida.getString("estado_compra"));

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Compra", ex);
        }

        return Optional.ofNullable(posibleCompra);
    }

    private List<Compra> crearCompras(ResultSet compraObtenida) throws SQLException {
        List<Compra> listaCompras = new ArrayList<>();
        while(compraObtenida.next()){
            Compra compra = new Compra();
            compra.setIdCompra(compraObtenida.getInt("id_compra"));
            compra.setDpiCliente(compraObtenida.getString("dpi_cliente"));
            compra.setTipoViaje(TipoViaje.valueOf( compraObtenida.getString("tipo_viaje")));
            compra.setFechaHoraCompra(compraObtenida.getTimestamp("fecha_hora_compra"));
            compra.setMontoTotal(compraObtenida.getDouble("monto_total"));
            compra.setEstadoCompra(compraObtenida.getString("estado_compra"));
            listaCompras.add(compra);
        }
        return listaCompras;
    }
}