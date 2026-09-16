/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;


import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.Chofer;
import com.mycompany.POJOs.Personal;
import com.mycompany.POJOs.Sucursal;
import com.mycompany.Enums.CargoPersonal;
import com.mycompany.Enums.EstadoOperativoChofer;
import com.mycompany.Excepciones.AccesoDeDatosException;
import com.mycompany.Excepciones.RegistroExistenteException;
import java.sql.Connection;
import java.sql.Date;
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
public class CrudChofer {

    private final String NOMBRE_ENTIDAD = "chofer";

    //sql
    private final String INSERTAR_CHOFER = "INSERT INTO " + NOMBRE_ENTIDAD + "(dpi_personal, no_licencia, tipo_licencia, fecha_vencimiento, sucursal_asignada, salario_base) VALUES(?,?,?,?,?,?)";
    private final String ACTUALIZAR_CHOFER = "UPDATE " + NOMBRE_ENTIDAD + " SET no_licencia = ?, tipo_licencia = ?, fecha_vencimiento = ?, sucursal_asignada = ?, salario_base = ? WHERE dpi_personal = ?";
    private final String ACTUALIZAR_ESTADO_OPERATIVO_CHOFER = "UPDATE " + NOMBRE_ENTIDAD + " SET estado_operativo = ? WHERE dpi_personal = ?";

    //sql queries
    private final String CONSULTAR_POR_DPI = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE dpi_personal = ?";




    public void insertarChofer(Chofer chofer) throws AccesoDeDatosException, RegistroExistenteException {

        String sql = INSERTAR_CHOFER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, chofer.getDpiPersonal());
            insertar.setString(2, chofer.getNoLicencia());
            insertar.setString(3, chofer.getTipoLicencia());
            insertar.setDate(4, Date.valueOf( chofer.getFechaVencimiento()));
            insertar.setString(5, chofer.getSucursalAsignada());
            insertar.setDouble(6, chofer.getSalarioBase());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new RegistroExistenteException("Registro ya existente ", ex);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar: ", ex);

        }

    }

    public void actualizarChofer(Chofer chofer) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_CHOFER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, chofer.getNoLicencia());
            actualizar.setString(2, chofer.getTipoLicencia());
            actualizar.setDate(3, Date.valueOf( chofer.getFechaVencimiento()));
            actualizar.setString(4, chofer.getSucursalAsignada());
            actualizar.setDouble(5, chofer.getSalarioBase());
            actualizar.setString(6, chofer.getDpiPersonal());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el chofer", ex);
        }
    }

    public void actualizarEstadoOperativo(Chofer chofer) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_ESTADO_OPERATIVO_CHOFER;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setString(1, chofer.getEstadoOperativo().name());
            actualizarEstado.setString(2, chofer.getDpiPersonal());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado Operativo del Chofer ", ex);
        }
    }


    public Optional<Chofer> consultarPorDpi(Personal chofer) throws AccesoDeDatosException {

        String dpi = chofer.getDpiPersonal();
        String sql = CONSULTAR_POR_DPI;
        Chofer posibleChofer = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, dpi);
            ResultSet choferObtenido = consultar.executeQuery();

            while (choferObtenido.next()) {
                posibleChofer = new Chofer();
                posibleChofer.setDpiPersonal(choferObtenido.getString("dpi_personal"));
                posibleChofer.setNoLicencia(choferObtenido.getString("no_licencia"));
                posibleChofer.setTipoLicencia(choferObtenido.getString("tipo_licencia"));
                posibleChofer.setFechaVencimiento((choferObtenido.getDate("fecha_vencimiento").toLocalDate()));
                posibleChofer.setSucursalAsignada(choferObtenido.getString("sucursal_asignada"));
                posibleChofer.setSalarioBase(choferObtenido.getDouble("salario_base"));
                posibleChofer.setEstadoOperativo(EstadoOperativoChofer.valueOf(choferObtenido.getString("estado_operativo")));
            }

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Chofer", ex);
        }
        
        return Optional.ofNullable(posibleChofer);
    }
    
    
    
    
    public List<Chofer> consultarChoferes() throws AccesoDeDatosException{
        CrudPersonal crudPersonal = new CrudPersonal();
        List<Chofer> listaChofer = new ArrayList<>();
        
        List<Personal> listaPersonal = crudPersonal.consultarPersonalPorCargo(CargoPersonal.CHOFER);
        for (Personal personal : listaPersonal) {
            Optional<Chofer> chofer = consultarPorDpi(personal);
            if (chofer.isPresent()) {
                listaChofer.add(chofer.get());
            }  
        }
        return listaChofer;
    }
    
    public List<Chofer> consultarChoferesPorSucursal(Sucursal sucursal) throws AccesoDeDatosException{
        CrudPersonal crudPersonal = new CrudPersonal();
        List<Chofer> listaChofer = new ArrayList<>();
        
        List<Personal> listaPersonal = crudPersonal.consultarPersonalPorCargo(CargoPersonal.CHOFER);
        for (Personal personal : listaPersonal) {
            Optional<Chofer> chofer = consultarPorDpi(personal);
            if (chofer.isPresent()) {
                if (chofer.get().getSucursalAsignada().equalsIgnoreCase(sucursal.getCodigoSucursal())) {
                    listaChofer.add(chofer.get());
                } 
            }  
        }
        return listaChofer;
    }
    
    
    
    
    
}
    
    
    

