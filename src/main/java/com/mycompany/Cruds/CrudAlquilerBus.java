/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.AlquilerBus;
import com.mycompany.Enums.EstadoAlquilerDeBus;
import com.mycompany.Excepciones.AccesoDeDatosException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
/**
 *
 * @author jonat
 */
public class CrudAlquilerBus {

    private final String NOMBRE_ENTIDAD = "alquiler_bus";

    //sql
    private final String INSERTAR_ALQUILER = "INSERT INTO " + NOMBRE_ENTIDAD + "(id_compra, id_viaje, origen, destino, kilometros, fecha_hora_Salida) VALUES(?,?,?,?,?,?)";
    private final String ACTUALIZAR_ALQUILER = "UPDATE " + NOMBRE_ENTIDAD + " SET origen = ?, destino = ?, kilometros = ?, fecha_hora_Salida = ? WHERE id_alquiler = ?";
    private final String ACTUALIZAR_ESTADO_ALQUILER = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE id_alquiler = ?";

    //sql queries
    private final String CONSULTAR_TODO_ALQUILER = "SELECT * FROM " + NOMBRE_ENTIDAD;
        private final String CONSULTAR_POR_SUCURSAL = "SELECT * FROM " + NOMBRE_ENTIDAD + " WHERE codigo_sucursal = ?";
    private final String CONSULTAR_POR_ID = "SELECT * FROM alquiler_bus WHERE id_alquiler = ?";

    public CrudAlquilerBus() {

    }

    public void insertarAlquilerBus(AlquilerBus alquiler) throws AccesoDeDatosException{

        String sql = INSERTAR_ALQUILER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setInt(1, alquiler.getIdCompra());
            insertar.setInt(2, alquiler.getIdViaje());
            insertar.setString(3, alquiler.getOrigen());
            insertar.setString(4, alquiler.getDestino());
            insertar.setDouble(5, alquiler.getKilometros());
            insertar.setTimestamp(6, alquiler.getFechaHoraSalida());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        }catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar la solicitud de Alquiler: ", ex);

        }

    }

    public void actualizarAlquilerBus(AlquilerBus alquiler) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_ALQUILER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, alquiler.getOrigen());
            actualizar.setString(2, alquiler.getDestino());
            actualizar.setDouble(3, alquiler.getKilometros());
            actualizar.setTimestamp(4, alquiler.getFechaHoraSalida());
            actualizar.setInt(5, alquiler.getIdAlquiler());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el alquiler de bus", ex);
        }
    }
    
    
    
    public void acutalizarEstado(AlquilerBus alquiler) throws AccesoDeDatosException {


        String sql = ACTUALIZAR_ESTADO_ALQUILER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setString(1, alquiler.getEstado().name());
            actualizarEstado.setInt(2, alquiler.getIdAlquiler());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado del Alquiler ", ex);
        }
    }


    public List<AlquilerBus> consultarAlquileres() throws AccesoDeDatosException {
        List<AlquilerBus> listaAlquileres = new ArrayList<>();
        String sql = CONSULTAR_TODO_ALQUILER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet alquilerObtenido = consultar.executeQuery();

            listaAlquileres = crearAlquileres(alquilerObtenido);
            
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los alquileres de bus ", ex);
        }

        return listaAlquileres;
        
    }

    
    public Optional<AlquilerBus> consultarPorId(AlquilerBus alquiler) throws AccesoDeDatosException {

        int id = alquiler.getIdAlquiler();
        String sql = CONSULTAR_POR_ID;
        AlquilerBus posibleAlquiler = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet alquilerObtenido = consultar.executeQuery();
            
            posibleAlquiler = new AlquilerBus();
            posibleAlquiler.setIdAlquiler(alquilerObtenido.getInt("id_alquiler"));
            posibleAlquiler.setIdCompra(alquilerObtenido.getInt("id_compra"));
            posibleAlquiler.setIdViaje(alquilerObtenido.getInt("id_viaje"));
            posibleAlquiler.setOrigen(alquilerObtenido.getString("origen"));
            posibleAlquiler.setDestino(alquilerObtenido.getString("destino"));
            posibleAlquiler.setKilometros(alquilerObtenido.getDouble("kilometros"));
            posibleAlquiler.setFechaHoraSalida(alquilerObtenido.getTimestamp("fecha_hora_Salida"));
            posibleAlquiler.setEstado(EstadoAlquilerDeBus.valueOf(alquilerObtenido.getString("estado")));
            

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Alquiler de Bus", ex);
        }

        return Optional.ofNullable(posibleAlquiler);
    }

    private List<AlquilerBus> crearAlquileres(ResultSet alquilerObtenido) throws SQLException {
        List<AlquilerBus> listaAlquileres = new ArrayList<>();
        
        while(alquilerObtenido.next()){
            AlquilerBus alquiler = new AlquilerBus();
            alquiler.setIdAlquiler(alquilerObtenido.getInt("id_alquiler"));
            alquiler.setIdCompra(alquilerObtenido.getInt("id_compra"));
            alquiler.setIdViaje(alquilerObtenido.getInt("id_viaje"));
            alquiler.setOrigen(alquilerObtenido.getString("origen"));
            alquiler.setDestino(alquilerObtenido.getString("destino"));
            alquiler.setKilometros(alquilerObtenido.getDouble("kilometros"));
            alquiler.setFechaHoraSalida(alquilerObtenido.getTimestamp("fecha_hora_Salida"));
            alquiler.setEstado(EstadoAlquilerDeBus.valueOf(alquilerObtenido.getString("estado")));
            listaAlquileres.add(alquiler);
        }
        return listaAlquileres;
    }
    
    
}
