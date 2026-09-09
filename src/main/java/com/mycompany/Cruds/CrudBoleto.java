/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.DTOs.Boleto;
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
public class CrudBoleto {

    private final String NOMBRE_ENTIDAD = "boleto";

    //sql
    private final String INSERTAR_BOLETO = "INSERT INTO " + NOMBRE_ENTIDAD + "(id_compra, id_viaje, id_asiento) VALUES(?,?,?)";
    private final String ACTUALIZAR_BOLETO = "UPDATE " + NOMBRE_ENTIDAD + " SET id_compra = ?, id_viaje = ?, id_asiento = ? WHERE id_boleto = ?";
    private final String ACTUALIZAR_ESTADO_BOLETO = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE id_boleto = ?";

    //sql queries
    private final String CONSULTAR_TODO_BOLETO = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_ID = "SELECT * FROM boleto WHERE id_boleto = ?";
    private final String CONSULTAR_POR_COMPRA = "SELECT * FROM boleto WHERE id_compra = ?";

    public CrudBoleto() {

    }

    public void insertarBoleto(Boleto boleto) throws AccesoDeDatosException{

        String sql = INSERTAR_BOLETO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setInt(1, boleto.getIdCompra());
            insertar.setInt(2, boleto.getIdViaje());
            insertar.setInt(3, boleto.getIdAsiento());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar: ", ex);

        }

    }

    public void actualizarBoleto(Boleto boleto) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_BOLETO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setInt(1, boleto.getIdCompra());
            actualizar.setInt(2, boleto.getIdViaje());
            actualizar.setInt(3, boleto.getIdAsiento());
            actualizar.setInt(5, boleto.getIdBoleto());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el boleto", ex);
        }
    }

    public void habilitarDeshabilitar(Boleto boleto) throws AccesoDeDatosException {
        boolean estadoActual;

        if (boleto.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }

        String sql = ACTUALIZAR_ESTADO_BOLETO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setInt(2, boleto.getIdBoleto());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado del Boleto ", ex);
        }
    }


    public List<Boleto> consultarBoletos() throws AccesoDeDatosException {
        List<Boleto> listaBoletos = new ArrayList<>();
        String sql = CONSULTAR_TODO_BOLETO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet boletoObtenido = consultar.executeQuery();

            listaBoletos = crearBoleto(boletoObtenido);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los boletos ", ex);
        }

        return listaBoletos;

    }

    public List<Boleto> consultarPorCompra(Boleto boleto) throws AccesoDeDatosException {
        List<Boleto> listaBoletos = new ArrayList<>();
        String sql = CONSULTAR_POR_COMPRA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, boleto.getIdCompra());
            ResultSet boletoObtenido = consultar.executeQuery();

            listaBoletos = crearBoleto(boletoObtenido);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar los boletos de la compra ", ex);
        }

        return listaBoletos;
    }

    public Optional<Boleto> consultarPorId(Boleto boleto) throws AccesoDeDatosException {

        int id = boleto.getIdBoleto();
        String sql = CONSULTAR_POR_ID;
        Boleto posibleBoleto = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet boletoObtenido = consultar.executeQuery();
            posibleBoleto = new Boleto();
            posibleBoleto.setIdBoleto(boletoObtenido.getInt("id_boleto"));
            posibleBoleto.setIdCompra(boletoObtenido.getInt("id_compra"));
            posibleBoleto.setIdViaje(boletoObtenido.getInt("id_viaje"));
            posibleBoleto.setIdAsiento(boletoObtenido.getInt("id_asiento"));
            posibleBoleto.setEstado(boletoObtenido.getBoolean("estado"));

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Boleto", ex);
        }

        return Optional.ofNullable(posibleBoleto);
    }

    private List<Boleto> crearBoleto(ResultSet boletoObtenido) throws SQLException {
        List<Boleto> listaBoletos = new ArrayList<>();
        
        while(boletoObtenido.next()){
            Boleto boleto = new Boleto();
            boleto.setIdBoleto(boletoObtenido.getInt("id_boleto"));
            boleto.setIdCompra(boletoObtenido.getInt("id_compra"));
            boleto.setIdViaje(boletoObtenido.getInt("id_viaje"));
            boleto.setIdAsiento(boletoObtenido.getInt("id_asiento"));
            boleto.setEstado(boletoObtenido.getBoolean("estado"));
            listaBoletos.add(boleto);
        }
        return listaBoletos;
    }
}
