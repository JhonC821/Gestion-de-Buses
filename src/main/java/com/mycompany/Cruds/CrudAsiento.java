/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.DTOs.Asiento;
import com.mycompany.Enums.EstadoAsiento;
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
public class CrudAsiento {

    private final String NOMBRE_ENTIDAD = "asiento";

    //sql
    private final String INSERTAR_ASIENTO = "INSERT INTO " + NOMBRE_ENTIDAD + "(no_placa, numero_asiento) VALUES(?,?)";
    private final String ACTUALIZAR_ASIENTO = "UPDATE " + NOMBRE_ENTIDAD + " SET no_placa = ?, numero_asiento = ? WHERE id_asiento = ?";
    private final String ACTUALIZAR_ESTADO_ASIENTO = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE id_asiento = ?";
    private final String ELIMINAR_ASIENTO = "DELETE FROM "+NOMBRE_ENTIDAD+"WHERE id_asiento = ?";

    //sql queries
    private final String CONSULTAR_POR_ID = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE id_asiento = ?";
    private final String CONSULTAR_POR_PLACA = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE no_placa = ?";

    public CrudAsiento() {

    }
    
    // crea un asiento, o inserta el asiento, el numero de asiento tiene que ser manejado antes
    public void insertarAsiento(Asiento asiento) throws AccesoDeDatosException{

        String sql = INSERTAR_ASIENTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, asiento.getNoPlaca());
            insertar.setInt(2, asiento.getNumeroAsiento());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar el asiento: ", ex);

        }

    }

    public void actualizarAsiento(Asiento asiento) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_ASIENTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, asiento.getNoPlaca());
            actualizar.setInt(2, asiento.getNumeroAsiento());
            actualizar.setInt(3, asiento.getIdAsiento());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el asiento", ex);
        }
    }

    public void actualizarEstadoAsiento(Asiento asiento) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_ESTADO_ASIENTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setString(1, asiento.getEstado().name());
            actualizarEstado.setInt(2, asiento.getIdAsiento());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado del Asiento ", ex);
        }
    }
    
    public void eliminarAsiento(Asiento asiento) throws AccesoDeDatosException{
        int id = asiento.getIdAsiento();
        String sql = ELIMINAR_ASIENTO;
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement eliminar = conexion.prepareStatement(sql)){
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        
        }catch(SQLException ex){
            throw new AccesoDeDatosException("Error al eliminar el asiento: ", ex);
        }
        
        
    }

    public List<Asiento> consultarPorPlaca(Asiento asiento) throws AccesoDeDatosException {
        List<Asiento> listaAsientos = new ArrayList<>();
        String sql = CONSULTAR_POR_PLACA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, asiento.getNoPlaca());
            ResultSet asientoObtenido = consultar.executeQuery();

            listaAsientos = crearAsiento(asientoObtenido);
            
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar los asientos del bus ", ex);
        }

        return listaAsientos;
    }

    public Optional<Asiento> consultarPorId(Asiento asiento) throws AccesoDeDatosException {

        int id = asiento.getIdAsiento();
        String sql = CONSULTAR_POR_ID;
        Asiento posibleAsiento = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet asientoObtenido = consultar.executeQuery();
            
            while(asientoObtenido.next()){
                posibleAsiento = new Asiento();
                posibleAsiento.setIdAsiento(asientoObtenido.getInt("id_asiento"));
                posibleAsiento.setNoPlaca(asientoObtenido.getString("no_placa"));
                posibleAsiento.setNumeroAsiento(asientoObtenido.getInt("numero_asiento"));
                posibleAsiento.setEstado(EstadoAsiento.valueOf(asientoObtenido.getString("estado")));
        }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Asiento", ex);
        }

        return Optional.ofNullable(posibleAsiento);
    }

    private List<Asiento> crearAsiento(ResultSet asientosObtenidos) throws SQLException {
        List<Asiento> listaAsientos = new ArrayList<>();
        
        while(asientosObtenidos.next()){
            Asiento asiento = new Asiento();
            asiento.setIdAsiento(asientosObtenidos.getInt("id_asiento"));
            asiento.setNoPlaca(asientosObtenidos.getString("no_placa"));
            asiento.setNumeroAsiento(asientosObtenidos.getInt("numero_asiento"));
            asiento.setEstado(EstadoAsiento.valueOf(asientosObtenidos.getString("estado")));
        }

        return listaAsientos;
    }
}
