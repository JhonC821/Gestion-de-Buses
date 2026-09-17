/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;
import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.ConfiguracionSistema;
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
public class CrudConfiguracionSistema {

    private final String NOMBRE_ENTIDAD = "configuracion_sistema";

    //sql
    private final String ACTUALIZAR_CONFIGURACION = "UPDATE " + NOMBRE_ENTIDAD + " SET valorConfiguracion = ? WHERE nombreConfiguracion = ?";

    //sql queriesss
    private final String CONSULTAR_POR_CLAVE = "SELECT * FROM " + NOMBRE_ENTIDAD + " WHERE nombreConfiguracion =?";
    private final String CONSULTAR_TODO_CONFIGURACION = "SELECT * FROM " + NOMBRE_ENTIDAD;


    public void actualizarConfiguracion(ConfiguracionSistema configuracion) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_CONFIGURACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setDouble(1, configuracion.getValor());
            actualizar.setString(2, configuracion.getClave());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar la Configuracion del Sistema", ex);
        }
    }


    public Optional<ConfiguracionSistema> consultarPorClave(ConfiguracionSistema configuracion) throws AccesoDeDatosException {

        String clave = configuracion.getClave();

        String sql = CONSULTAR_POR_CLAVE;
        ConfiguracionSistema posibleConfiguracion = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, clave);
            ResultSet configuracionObtenida = consultar.executeQuery();

            while (configuracionObtenida.next()) {
                posibleConfiguracion = new ConfiguracionSistema();
                posibleConfiguracion.setClave(configuracionObtenida.getString("nombreConfiguracion"));
                posibleConfiguracion.setValor(configuracionObtenida.getDouble("valorConfiguracion"));
            }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar la Configuracion del Sistema", ex);
        }

        return Optional.ofNullable(posibleConfiguracion);
    }
    
    public List<ConfiguracionSistema> consultarTodasConfiguraciones() throws AccesoDeDatosException {
        List<ConfiguracionSistema> listaConfiguraciones = new ArrayList<>();
        String sql = CONSULTAR_TODO_CONFIGURACION;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet configuracionObtenida = consultar.executeQuery();

            while (configuracionObtenida.next()) {
                ConfiguracionSistema nuevaConfiguracion = new ConfiguracionSistema();
                nuevaConfiguracion.setClave(configuracionObtenida.getString("nombreConfiguracion"));
                nuevaConfiguracion.setValor(configuracionObtenida.getDouble("valorConfiguracion"));
                listaConfiguraciones.add(nuevaConfiguracion);
            }
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todas las Configuraciones del Sistema ", ex);
        }

        return listaConfiguraciones;
    }
}
