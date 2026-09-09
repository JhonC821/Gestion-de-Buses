/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;


import com.mycompany.Conexion.Conexion;
import com.mycompany.DTOs.Bus;
import com.mycompany.DTOs.Sucursal;
import com.mycompany.Enums.EstadoOperativoBus;
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
public class CrudBus {

    private final String NOMBRE_ENTIDAD = "bus";

    //sql
    private final String INSERTAR_BUS = "INSERT INTO " + NOMBRE_ENTIDAD + "(no_placa, modelo, marca, codigo_sucursal, sucursal_actual, anio_fabricacion, capacidad, foto, kilometraje_actual) VALUES(?,?,?,?,?,?,?,?,?)";
    private final String ACTUALIZAR_BUS = "UPDATE " + NOMBRE_ENTIDAD + " SET modelo = ?, marca = ?, codigo_sucursal = ?, sucursal_actual = ?, anio_fabricacion = ?, capacidad = ?, foto = ?, kilometraje_actual = ? WHERE no_placa = ?";
    private final String ACTUALIZAR_ESTADO_OPERATIVO_BUS = "UPDATE " + NOMBRE_ENTIDAD + " SET estado_operativo = ? WHERE no_placa = ?";
    private final String ACTUALIZAR_ESTADO_BUS = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE no_placa = ?";

    //sql queries
    private final String CONSULTAR_TODO_BUS = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_PLACA = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE no_placa = ?";
    private final String CONSULTAR_POR_SUCURSAL = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE codigo_sucursal = ?";

    public CrudBus() {

    }

    public void insertarBus(Bus bus) throws AccesoDeDatosException, RegistroExistenteException {

        String sql = INSERTAR_BUS;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, bus.getNoPlaca());
            insertar.setString(2, bus.getModelo());
            insertar.setString(3, bus.getMarca());
            insertar.setString(4, bus.getCodigoSucursal());
            insertar.setString(5, bus.getSucursalActual());
            insertar.setInt(6, bus.getAnioFabricacion());
            insertar.setInt(7, bus.getCapacidad());
            insertar.setBytes(8, bus.getFoto());
            insertar.setDouble(9, bus.getKilometrajeActual());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new RegistroExistenteException("El numero de placa ya existe: ", ex);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar el Bus: ", ex);

        }

    }

    public void actualizarBus(Bus bus) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_BUS;

        try (Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, bus.getModelo());
            actualizar.setString(2, bus.getMarca());
            actualizar.setString(3, bus.getCodigoSucursal());
            actualizar.setString(4, bus.getSucursalActual());
            actualizar.setInt(5, bus.getAnioFabricacion());
            actualizar.setInt(6, bus.getCapacidad());
            actualizar.setBytes(7, bus.getFoto());
            actualizar.setDouble(8, bus.getKilometrajeActual());
            actualizar.setString(9, bus.getNoPlaca());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el bus", ex);
        }
    }

    public void actualizarEstadoOperativo(Bus bus) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_ESTADO_OPERATIVO_BUS;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setString(1, bus.getEstadoOperativo().name());
            actualizarEstado.setString(2, bus.getNoPlaca());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado Operativo del Bus ", ex);
        }
    }

    public void habilitarDeshabilitar(Bus bus) throws AccesoDeDatosException {
        boolean estadoActual;

        if (bus.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }

        String sql = ACTUALIZAR_ESTADO_BUS;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setString(2, bus.getNoPlaca());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado del Bus ", ex);
        }
    }


    public List<Bus> consultarBuses() throws AccesoDeDatosException {
        List<Bus> listaBuses = new ArrayList<>();
        String sql = CONSULTAR_TODO_BUS;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet busesObtenidos = consultar.executeQuery();
            listaBuses = crearBuses(busesObtenidos);
           
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los buses ", ex);
        }

        return listaBuses;

    }
    
        public List<Bus> consultarBusesPosSucursal(Sucursal sucursal) throws AccesoDeDatosException {
        String codigoSucursal = sucursal.getCodigoSucursal();
        List<Bus> listaBuses = new ArrayList<>();
        String sql = CONSULTAR_TODO_BUS;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, codigoSucursal);
            ResultSet busesObtenidos = consultar.executeQuery();
            
            listaBuses = crearBuses(busesObtenidos);
           
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los buses ", ex);
        }

        return listaBuses;

    }
    

    public Optional<Bus> consultarPorPlaca(String bus) throws AccesoDeDatosException {

        String placa = bus;
        String sql = CONSULTAR_POR_PLACA;
        Bus posibleBus = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, placa);
            ResultSet busObtenido = consultar.executeQuery();

            while (busObtenido.next()) {
                posibleBus = new Bus();
                posibleBus.setNoPlaca(busObtenido.getString("no_placa"));
                posibleBus.setModelo(busObtenido.getString("modelo"));
                posibleBus.setMarca(busObtenido.getString("marca"));
                posibleBus.setCodigoSucursal(busObtenido.getString("codigo_sucursal"));
                posibleBus.setSucursalActual(busObtenido.getString("sucursal_actual"));
                posibleBus.setAnioFabricacion(busObtenido.getInt("anio_fabricacion"));
                posibleBus.setCapacidad(busObtenido.getInt("capacidad"));
                posibleBus.setFoto(busObtenido.getBytes("foto"));
                posibleBus.setKilometrajeActual(busObtenido.getDouble("kilometraje_actual"));
                posibleBus.setEstadoOperativo(EstadoOperativoBus.valueOf(busObtenido.getString("estado_operativo")));
                posibleBus.setEstado(busObtenido.getBoolean("estado"));
            }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Bus", ex);
        }

        return Optional.ofNullable(posibleBus);
    }
    
    private List<Bus> crearBuses(ResultSet busObtenido) throws SQLException{
        List<Bus> listaBuses = new ArrayList<>();
         while (busObtenido.next()) {
                Bus nuevoBus = new Bus();
                nuevoBus.setNoPlaca(busObtenido.getString("no_placa"));
                nuevoBus.setModelo(busObtenido.getString("modelo"));
                nuevoBus.setMarca(busObtenido.getString("marca"));
                nuevoBus.setCodigoSucursal(busObtenido.getString("codigo_sucursal"));
                nuevoBus.setSucursalActual(busObtenido.getString("sucursal_actual"));
                nuevoBus.setAnioFabricacion(busObtenido.getInt("anio_fabricacion"));
                nuevoBus.setCapacidad(busObtenido.getInt("capacidad"));
                nuevoBus.setFoto(busObtenido.getBytes("foto"));
                nuevoBus.setKilometrajeActual(busObtenido.getDouble("kilometraje_actual"));
                nuevoBus.setEstadoOperativo(EstadoOperativoBus.valueOf(busObtenido.getString("estado_operativo")));
                nuevoBus.setEstado(busObtenido.getBoolean("estasdo"));
                listaBuses.add(nuevoBus);
            }
         
         return listaBuses;
    }
}
