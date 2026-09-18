/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;

import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.GastoTaller;
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
public class CrudGastoTaller {

    private final String NOMBRE_ENTIDAD = "gasto_taller";

    //sql
    private final String INSERTAR_GASTO = "INSERT INTO " + NOMBRE_ENTIDAD + "(no_placa, fecha_gasto, descripcion, monto_repuesto, monto_mano_obra, monto_total) VALUES(?,?,?,?,?,?)";
    private final String ACTUALIZAR_GASTO = "UPDATE " + NOMBRE_ENTIDAD + " SET no_placa = ?, fecha_gasto = ?, descripcion = ?, monto_repuesto = ?, monto_mano_obra = ?, monto_total = ? WHERE id_gasto = ?";
    private final String ELIMINAR_GASTO = "DELETE FROM " + NOMBRE_ENTIDAD + " WHERE id_gasto = ?";

    //sql queries
    private final String CONSULTAR_TODO_GASTO = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_ID = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE id_gasto = ?";
    private final String CONSULTAR_POR_PLACA = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE no_placa = ?";

    public CrudGastoTaller() {

    }
    // inserta el gasto, pero el total tiene que ser calcularlo antes de meter esta mamada
    public void insertarGastoTaller(GastoTaller gasto) throws AccesoDeDatosException{

        String sql = INSERTAR_GASTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, gasto.getNoPlaca());
            insertar.setDate(2, gasto.getFechaGasto());
            insertar.setString(3, gasto.getDescripcion());
            insertar.setDouble(4, gasto.getMontoRepuesto());
            insertar.setDouble(5, gasto.getMontoManoObra());
            insertar.setDouble(6, gasto.getMontoTotal());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        }catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar el Gasto de taller: ", ex);

        }

    }

    public void actualizarGastoTaller(GastoTaller gasto) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_GASTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, gasto.getNoPlaca());
            actualizar.setDate(2, gasto.getFechaGasto());
            actualizar.setString(3, gasto.getDescripcion());
            actualizar.setDouble(4, gasto.getMontoRepuesto());
            actualizar.setDouble(5, gasto.getMontoManoObra());
            actualizar.setDouble(6, gasto.getMontoTotal());
            actualizar.setInt(7, gasto.getIdGasto());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el gasto de taller", ex);
        }
    }

    public void eliminarGastoTaller(GastoTaller gasto) throws AccesoDeDatosException {
        String sql = ELIMINAR_GASTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement eliminar = conexion.prepareStatement(sql)) {
            eliminar.setInt(1, gasto.getIdGasto());
            eliminar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al eliminar el gasto de taller", ex);
        }
    }


    public List<GastoTaller> consultarGastosTaller() throws AccesoDeDatosException {
        List<GastoTaller> listaGastos = new ArrayList<>();
        String sql = CONSULTAR_TODO_GASTO;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet gastoObtenido = consultar.executeQuery();

            listaGastos = crearGastoTaller(gastoObtenido);
            
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los gastos de taller ", ex);
        }

        return listaGastos;

    }

    public List<GastoTaller> consultarPorPlaca(GastoTaller gasto) throws AccesoDeDatosException {
        List<GastoTaller> listaGastos = new ArrayList<>();
        String sql = CONSULTAR_POR_PLACA;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, gasto.getNoPlaca());
            ResultSet gastoObtenido = consultar.executeQuery();

            listaGastos = crearGastoTaller(gastoObtenido);
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar los gastos del bus ", ex);
        }

        return listaGastos;
    }

    public Optional<GastoTaller> consultarPorId(GastoTaller gasto) throws AccesoDeDatosException {
  
        int id = gasto.getIdGasto();
        String sql = CONSULTAR_POR_ID;
        GastoTaller posibleGasto = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setInt(1, id);
            ResultSet gastoObtenido = consultar.executeQuery();
           
            while(gastoObtenido.next()){
                posibleGasto = new GastoTaller();
                posibleGasto.setIdGasto(gastoObtenido.getInt("id_gasto"));
                posibleGasto.setNoPlaca(gastoObtenido.getString("no_placa"));
                posibleGasto.setFechaGasto(gastoObtenido.getDate("fecha_gasto"));
                posibleGasto.setDescripcion(gastoObtenido.getString("descripcion"));
                posibleGasto.setMontoRepuesto(gastoObtenido.getDouble("monto_repuesto"));
                posibleGasto.setMontoManoObra(gastoObtenido.getDouble("monto_mano_obra"));
                posibleGasto.setMontoTotal(gastoObtenido.getDouble("monto_total"));
            }
  

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Gasto de Taller", ex);
        }

        return Optional.ofNullable(posibleGasto);
    }

    private List<GastoTaller> crearGastoTaller(ResultSet gastoObtenido) throws SQLException {
        List<GastoTaller> gastos = new ArrayList<>();
        
        while(gastoObtenido.next()){
            GastoTaller gasto = new GastoTaller();
            gasto.setIdGasto(gastoObtenido.getInt("id_gasto"));
            gasto.setNoPlaca(gastoObtenido.getString("no_placa"));
            gasto.setFechaGasto(gastoObtenido.getDate("fecha_gasto"));
            gasto.setDescripcion(gastoObtenido.getString("descripcion"));
            gasto.setMontoRepuesto(gastoObtenido.getDouble("monto_repuesto"));
            gasto.setMontoManoObra(gastoObtenido.getDouble("monto_mano_obra"));
            gasto.setMontoTotal(gastoObtenido.getDouble("monto_total"));
            gastos.add(gasto);
        }

        return gastos;
    }
}
