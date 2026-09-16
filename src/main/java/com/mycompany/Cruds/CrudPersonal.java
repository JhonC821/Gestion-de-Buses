/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;


import com.mycompany.Conexion.Conexion;
import com.mycompany.POJOs.Personal;
import com.mycompany.Enums.CargoPersonal;
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
public class CrudPersonal {
    
    private final String NOMBRE_ENTIDAD = "personal";
    
    
    //sql
    private final String INSERTAR_PERSONAL = "INSERT INTO "+NOMBRE_ENTIDAD+"(dpi_personal, nombre_completo, apellido_completo, telefono, cargo, usuario, contrasenia) VALUES(?,?,?,?,?,?,?)";
    private final String ACTUALIZAR_PERSONAL = "UPDATE "+NOMBRE_ENTIDAD+ " SET  nombre_completo = ?, apellido_completo = ?, telefono = ?, cargo = ?,  usuario = ?, contrasenia = ? WHERE dpi_personal = ?";
    private final String ACTUALIZAR_ESTADO_PERSONAL = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE dpi_personal = ?";
    
    //sql queris
    private final String CONSULTAR_TODO_PERSONAL = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_DPI = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE dpi_personal = ?";
    private final String CONSULTAR_POR_CARGO = "SELECT *FROM " + NOMBRE_ENTIDAD + " WHERE cargo = ? AND estado = TRUE";
    
    
    public CrudPersonal(){
    
    }
    
    public void insertarPersonal(Personal personal) throws AccesoDeDatosException, RegistroExistenteException{
        
        String sql = INSERTAR_PERSONAL;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement insertar = conexion.prepareStatement(sql)){
            insertar.setString(1,personal.getDpiPersonal());
            insertar.setString(2, personal.getNombreCompleto());
            insertar.setString(3, personal.getApellidoCompleto());
            insertar.setString(4, personal.getTelefono());
            insertar.setString(5, personal.getCargo().name());
            insertar.setString(6, personal.getUsuario());
            insertar.setString(7, personal.getContrasenia());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);
        
        }catch(SQLIntegrityConstraintViolationException ex){    
            throw new RegistroExistenteException("Registro ya existente ", ex);
            
        }catch(SQLException ex){
            
            throw new AccesoDeDatosException("Error al insertar: ", ex);
        
        }
        
    }
    
    public void actualizarPersonal(Personal personal) throws AccesoDeDatosException{
        String sql = ACTUALIZAR_PERSONAL;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement actualizar = conexion.prepareStatement(sql)){
            actualizar.setString(1, personal.getNombreCompleto());
            actualizar.setString(2, personal.getApellidoCompleto());
            actualizar.setString(3, personal.getTelefono());
            actualizar.setString(4, personal.getCargo().name());
            actualizar.setString(5, personal.getUsuario());
            actualizar.setString(6, personal.getContrasenia());
            actualizar.setString(7, personal.getDpiPersonal());
            actualizar.executeUpdate();
        
        }catch(SQLException ex){
            
            throw new AccesoDeDatosException("Error al Actualizar el personal", ex);
        }
    }
    
    public void habilitarDeshabilitar(Personal personal) throws AccesoDeDatosException{
        boolean estadoActual;
        
        if (personal.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }
        
        String sql = ACTUALIZAR_ESTADO_PERSONAL;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement actualizarEstado = conexion.prepareStatement(sql)){
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setString(2, personal.getDpiPersonal());
            actualizarEstado.executeUpdate();
        
        }catch(SQLException ex){
            
            throw new AccesoDeDatosException("Error al actualizar el Estado del Personal ", ex);
        }
    }
    
    
    public List<Personal> consultarPersonal() throws AccesoDeDatosException{
        List<Personal> listaPersonal = new ArrayList<>();
        String sql = CONSULTAR_TODO_PERSONAL;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement consultar = conexion.prepareStatement(sql) ){
            ResultSet personalObtenido = consultar.executeQuery();
            listaPersonal = crearPersonal(personalObtenido);
            
        }catch(SQLException ex ){
            
            throw new AccesoDeDatosException("Error al consultar todo el personal ", ex);
        }
        
        return listaPersonal;
    
    }
    
    public Optional<Personal> consultarPorDpi(String dpiPersonal) throws AccesoDeDatosException{

        String dpi = dpiPersonal;
        String sql = CONSULTAR_POR_DPI;
        Personal posiblePersonal = null;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement consultar = conexion.prepareStatement(sql)){
            consultar.setString(1, dpi);
            ResultSet personalObtenido = consultar.executeQuery();
            
            while(personalObtenido.next()){
                posiblePersonal = new Personal();
                posiblePersonal.setDpiPersonal(personalObtenido.getString("dpi_personal"));
                posiblePersonal.setNombreCompleto(personalObtenido.getString("nombre_completo"));
                posiblePersonal.setApellidoCompleto(personalObtenido.getString("apellido_completo"));
                posiblePersonal.setTelefono(personalObtenido.getString("telefono"));
                posiblePersonal.setCargo(CargoPersonal.valueOf(personalObtenido.getString("cargo")));
                posiblePersonal.setUsuario(personalObtenido.getString("usuario"));
                posiblePersonal.setContrasenia(personalObtenido.getString("contrasenia"));
                posiblePersonal.setEstado(personalObtenido.getBoolean("estado"));
            }
           
        } catch(SQLException ex){
            
            throw new AccesoDeDatosException("Error al consultar el Personal", ex);
        }
            
        return Optional.ofNullable(posiblePersonal);
    }
    
    public List<Personal> consultarPersonalPorCargo(CargoPersonal cargoPersonal) throws AccesoDeDatosException{
        List<Personal> listaPersonal = new ArrayList<>();
        String cargo = cargoPersonal.name();
        String sql = CONSULTAR_POR_CARGO;
        
        try(Connection conexion = Conexion.getInstance().getConexion();
            PreparedStatement consultar = conexion.prepareStatement(sql)){
            consultar.setString(1, cargo);
            ResultSet personalObtenido = consultar.executeQuery();
            listaPersonal = crearPersonal(personalObtenido);
        
        } catch(SQLException ex){
            
            throw new AccesoDeDatosException("Error al consultar por cargo ", ex);
        }
        
        return listaPersonal;
    
    }
    
    private List<Personal> crearPersonal(ResultSet personalObtenido) throws SQLException{
        List<Personal> listaPersonal = new ArrayList<>();
         while(personalObtenido.next()){
                Personal nuevoPersonal = new Personal();
                nuevoPersonal.setDpiPersonal(personalObtenido.getString("dpi_personal"));
                nuevoPersonal.setNombreCompleto(personalObtenido.getString("nombre_completo"));
                nuevoPersonal.setApellidoCompleto(personalObtenido.getString("apellido_completo"));
                nuevoPersonal.setTelefono(personalObtenido.getString("telefono"));
                nuevoPersonal.setCargo(CargoPersonal.valueOf( personalObtenido.getString("cargo")));
                nuevoPersonal.setUsuario(personalObtenido.getString("usuario"));
                nuevoPersonal.setContrasenia(personalObtenido.getString("contrasenia"));
                nuevoPersonal.setEstado(personalObtenido.getBoolean("estado"));
                listaPersonal.add(nuevoPersonal);
            }
        return listaPersonal; 
    }
    
    
}
