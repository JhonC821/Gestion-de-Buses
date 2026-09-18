/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.POJOs.Viaje;
import com.mycompany.Enums.EstadoViaje;
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
public class CrudViaje {

    private final String NOMBRE_ENTIDAD = "viaje";

    //sql
    private final String INSERTAR_VIAJE = "INSERT INTO " + NOMBRE_ENTIDAD + "(no_placa, dpi_personal, codigo_ruta, fecha_hora_salida, kilometraje_inicial_bus) VALUES(?,?,?,?,?)";
    private final String ACTUALIZAR_VIAJE = "UPDATE " + NOMBRE_ENTIDAD + " SET no_placa = ?, dpi_personal = ?, codigo_ruta = ?, fecha_hora_salida = ?, kilometraje_inicial_bus = ? WHERE id_viaje = ?";
    private final String FINALIZAR_VIAJE = "UPDATE " + NOMBRE_ENTIDAD + " SET fecha_hora_llegada = ?, kilometraje_final_bus = ?, combustible_consumido = ?, depreciacion_bus = ?, monto_total = ? WHERE id_viaje = ?";
    private final String ACTUALIZAR_ESTADO_VIAJE = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE id_viaje = ?";

    //sql queries
    private final String CONSULTAR_TODO_VIAJE = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_ID = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE id_viaje = ?";
    private final String CONSULTAR_POR_SUCURSAL = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE codigo_sucursal = ?";

    public CrudViaje() {

    }

    public void insertarViaje(Viaje viaje) throws AccesoDeDatosException{

        String sql = INSERTAR_VIAJE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, viaje.getNoPlaca());
            insertar.setString(2, viaje.getDpiPersonal());
            insertar.setString(3, viaje.getCodigoRuta());
            insertar.setTimestamp(4, viaje.getFechaHoraSalida());
            insertar.setDouble(5, viaje.getKilometrajeInicialBus());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        }catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar el viaje: ", ex);

        }

    }

    public void actualizarViaje(Viaje viaje) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_VIAJE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, viaje.getNoPlaca());
            actualizar.setString(2, viaje.getDpiPersonal());
            actualizar.setString(3, viaje.getCodigoRuta());
            actualizar.setTimestamp(4, viaje.getFechaHoraSalida());
            actualizar.setDouble(5, viaje.getKilometrajeInicialBus());
            actualizar.setInt(6, viaje.getIdViaje());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el viaje", ex);
        }
    }

    public void finalizarViaje(Viaje viaje) throws AccesoDeDatosException {
        String sql = FINALIZAR_VIAJE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement finalizar = conexion.prepareStatement(sql)) {
            finalizar.setTimestamp(1, viaje.getFechaHoraLlegada());
            finalizar.setDouble(2, viaje.getKilometrajeFinalBus());
            finalizar.setDouble(3, viaje.getCombustibleConsumido());
            finalizar.setDouble(4, viaje.getDepreciacionBus());
            finalizar.setDouble(5, viaje.getMontoTotal());
            finalizar.setInt(6, viaje.getIdViaje());
            finalizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al finalizar el viaje", ex);
        }
    }

    public void actualizarEstadoViaje(Viaje viaje) throws AccesoDeDatosException {

        String sql = ACTUALIZAR_ESTADO_VIAJE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setString(1, viaje.getEstado().name());
            actualizarEstado.setInt(2, viaje.getIdViaje());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado del Viaje ", ex);
        }
    }


    public List<Viaje> consultarViajes() throws AccesoDeDatosException {
        List<Viaje> listaViajes = new ArrayList<>();
        String sql = CONSULTAR_TODO_VIAJE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet viajeObtenido = consultar.executeQuery();

            listaViajes = crearViaje(viajeObtenido);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los viajes ", ex);
        }

        return listaViajes;

    }
    
    public List<Viaje> consultarPosSucursal(Sucursal sucursal) throws AccesoDeDatosException{
        List<Viaje> listaViajes = new ArrayList<>();
        String codigoSucursal = sucursal.getCodigoSucursal();
        String sql = CONSULTAR_POR_SUCURSAL;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement consultar = conexion.prepareStatement(sql)){
            consultar.setString(1,codigoSucursal);
            ResultSet viajesObtenidos = consultar.executeQuery();
            listaViajes = crearViaje(viajesObtenidos);
            
            
        }catch(SQLException ex){
            throw new AccesoDeDatosException("Error al consultar viajes por sucursal: ", ex);
            
        }
        return listaViajes;
    }

    public Optional<Viaje> consultarPorId(Viaje viaje) throws AccesoDeDatosException {

        int id = viaje.getIdViaje();
        String sql = CONSULTAR_POR_ID;
        Viaje posibleViaje = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet viajeObtenido = consultar.executeQuery();
            
            while(viajeObtenido.next()){
                posibleViaje = new Viaje();
                posibleViaje.setIdViaje(viajeObtenido.getInt("id_viaje"));
                posibleViaje.setNoPlaca(viajeObtenido.getString("no_placa"));
                posibleViaje.setDpiPersonal(viajeObtenido.getString("dpi_personal"));
                posibleViaje.setCodigoRuta(viajeObtenido.getString("codigo_ruta"));
                posibleViaje.setFechaHoraSalida(viajeObtenido.getTimestamp("fecha_hora_salida"));
                posibleViaje.setFechaHoraLlegada(viajeObtenido.getTimestamp("fecha_hora_llegada"));
                posibleViaje.setKilometrajeInicialBus(viajeObtenido.getDouble("kilometraje_inicial_bus"));
                posibleViaje.setKilometrajeFinalBus(viajeObtenido.getDouble("kilometraje_final_bus"));
                posibleViaje.setCombustibleConsumido(viajeObtenido.getDouble("combustible_consumido"));
                posibleViaje.setDepreciacionBus(viajeObtenido.getDouble("depreciacion_bus"));
                posibleViaje.setMontoTotal(viajeObtenido.getDouble("monto_total"));
                posibleViaje.setEstado(EstadoViaje.valueOf(viajeObtenido.getString("estado")));
            }
                 
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Viaje por Id", ex);
        }

        return Optional.ofNullable(posibleViaje);
    }

    private List<Viaje> crearViaje(ResultSet viajeObtenido) throws SQLException {
        
        List<Viaje> listaViajes = new ArrayList<>();
        while(viajeObtenido.next()){
            Viaje viaje = new Viaje();
            viaje.setIdViaje(viajeObtenido.getInt("id_viaje"));
            viaje.setNoPlaca(viajeObtenido.getString("no_placa"));
            viaje.setDpiPersonal(viajeObtenido.getString("dpi_personal"));
            viaje.setCodigoRuta(viajeObtenido.getString("codigo_ruta"));
            viaje.setFechaHoraSalida(viajeObtenido.getTimestamp("fecha_hora_salida"));
            viaje.setFechaHoraLlegada(viajeObtenido.getTimestamp("fecha_hora_llegada"));
            viaje.setKilometrajeInicialBus(viajeObtenido.getDouble("kilometraje_inicial_bus"));
            viaje.setKilometrajeFinalBus(viajeObtenido.getDouble("kilometraje_final_bus"));
            viaje.setCombustibleConsumido(viajeObtenido.getDouble("combustible_consumido"));
            viaje.setDepreciacionBus(viajeObtenido.getDouble("depreciacion_bus"));
            viaje.setMontoTotal(viajeObtenido.getDouble("monto_total"));
            viaje.setEstado(EstadoViaje.valueOf(viajeObtenido.getString("estado")));
            listaViajes.add(viaje);
        }

        return listaViajes;
    }
}
