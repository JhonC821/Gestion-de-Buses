/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Cruds;


import com.mycompany.Conexion.Conexion;
import com.mycompany.DTOs.Cliente;
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
public class CrudCliente {

    private final String NOMBRE_ENTIDAD = "cliente";

    //sql
    private final String INSERTAR_CLIENTE = "INSERT INTO " + NOMBRE_ENTIDAD + "(dpi_cliente, nit_cliente, nombre_completo, apellido_completo, telefono, usuario, contrasenia) VALUES(?,?,?,?,?,?,?)";
    private final String ACTUALIZAR_CLIENTE = "UPDATE " + NOMBRE_ENTIDAD + " SET nit_cliente = ?, nombre_completo = ?, apellido_completo = ?, telefono = ?, usuario = ?, contrasenia = ? WHERE dpi_cliente = ?";
    private final String ACTUALIZAR_SALDO_CLIENTE = "UPDATE " + NOMBRE_ENTIDAD + " SET saldo = ? WHERE dpi_cliente = ?";
    private final String ACTUALIZAR_ESTADO_CLIENTE = "UPDATE " + NOMBRE_ENTIDAD + " SET estado = ? WHERE dpi_cliente = ?";

    //sql queries
    private final String CONSULTAR_TODO_CLIENTE = "SELECT * FROM " + NOMBRE_ENTIDAD;
    private final String CONSULTAR_POR_DPI = "SELECT * FROM "+NOMBRE_ENTIDAD+" WHERE dpi_cliente = ?";

    public CrudCliente() {

    }

    public void insertarCliente(Cliente cliente) throws AccesoDeDatosException, RegistroExistenteException {

        String sql = INSERTAR_CLIENTE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement insertar = conexion.prepareStatement(sql)) {
            insertar.setString(1, cliente.getDpiCliente());
            insertar.setString(2, cliente.getNitCliente());
            insertar.setString(3, cliente.getNombreCompleto());
            insertar.setString(4, cliente.getApellidoCompleto());
            insertar.setString(5, cliente.getTelefono());
            insertar.setString(6, cliente.getUsuario());
            insertar.setString(7, cliente.getContrasenia());

            int filas = insertar.executeUpdate();
            System.out.println("Se inserto: " + filas);

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new RegistroExistenteException("Registro de cliente ya existente ", ex);

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al insertar: ", ex);

        }

    }

    public void actualizarCliente(Cliente cliente) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_CLIENTE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizar = conexion.prepareStatement(sql)) {
            actualizar.setString(1, cliente.getNitCliente());
            actualizar.setString(2, cliente.getNombreCompleto());
            actualizar.setString(3, cliente.getApellidoCompleto());
            actualizar.setString(4, cliente.getTelefono());
            actualizar.setString(5, cliente.getUsuario());
            actualizar.setString(6, cliente.getContrasenia());
            actualizar.setString(7, cliente.getDpiCliente());
            actualizar.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al Actualizar el cliente", ex);
        }
    }

    public void actualizarSaldo(Cliente cliente) throws AccesoDeDatosException {
        String sql = ACTUALIZAR_SALDO_CLIENTE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarSaldo = conexion.prepareStatement(sql)) {
            actualizarSaldo.setDouble(1, cliente.getSaldo());
            actualizarSaldo.setString(2, cliente.getDpiCliente());
            actualizarSaldo.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Saldo del Cliente ", ex);
        }
    }

    public void habilitarDeshabilitar(Cliente cliente) throws AccesoDeDatosException {
        boolean estadoActual;

        if (cliente.isEstado()) {
            estadoActual = false;
        } else {
            estadoActual = true;
        }

        String sql = ACTUALIZAR_ESTADO_CLIENTE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement actualizarEstado = conexion.prepareStatement(sql)) {
            actualizarEstado.setBoolean(1, estadoActual);
            actualizarEstado.setString(2, cliente.getDpiCliente());
            actualizarEstado.executeUpdate();

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al actualizar el Estado del Cliente ", ex);
        }
    }


    public List<Cliente> consultarClientes() throws AccesoDeDatosException {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = CONSULTAR_TODO_CLIENTE;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            ResultSet clienteObtenido = consultar.executeQuery();

            listaClientes = crearCliente(clienteObtenido);
            
        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar todos los clientes ", ex);
        }

        return listaClientes;

    }

    public Optional<Cliente> consultarPorDpi(Cliente cliente) throws AccesoDeDatosException {

        String dpi = cliente.getDpiCliente();
        String sql = CONSULTAR_POR_DPI;
        Cliente posibleCliente = null;

        try (Connection conexion = Conexion.getInstance().getConexion();
             PreparedStatement consultar = conexion.prepareStatement(sql)) {
            consultar.setString(1, dpi);
            ResultSet clienteObtenido = consultar.executeQuery();

            posibleCliente = new Cliente();
            posibleCliente.setDpiCliente(clienteObtenido.getString("dpi_cliente"));
            posibleCliente.setNitCliente(clienteObtenido.getString("nit_cliente"));
            posibleCliente.setNombreCompleto(clienteObtenido.getString("nombre_completo"));
            posibleCliente.setApellidoCompleto(clienteObtenido.getString("apellido_completo"));
            posibleCliente.setTelefono(clienteObtenido.getString("telefono"));
            posibleCliente.setSaldo(clienteObtenido.getDouble("saldo"));
            posibleCliente.setUsuario(clienteObtenido.getString("usuario"));
            posibleCliente.setContrasenia(clienteObtenido.getString("contrasenia"));
            posibleCliente.setEstado(clienteObtenido.getBoolean("estado"));

        } catch (SQLException ex) {

            throw new AccesoDeDatosException("Error al consultar el Cliente", ex);
        }

        return Optional.ofNullable(posibleCliente);
    }

    private List<Cliente> crearCliente(ResultSet clienteObtenido) throws SQLException {
        List<Cliente> listaClientes = new ArrayList<>();
        
        while(clienteObtenido.next()){
            Cliente cliente = new Cliente();
            cliente.setDpiCliente(clienteObtenido.getString("dpi_cliente"));
            cliente.setNitCliente(clienteObtenido.getString("nit_cliente"));
            cliente.setNombreCompleto(clienteObtenido.getString("nombre_completo"));
            cliente.setApellidoCompleto(clienteObtenido.getString("apellido_completo"));
            cliente.setTelefono(clienteObtenido.getString("telefono"));
            cliente.setSaldo(clienteObtenido.getDouble("saldo"));
            cliente.setUsuario(clienteObtenido.getString("usuario"));
            cliente.setContrasenia(clienteObtenido.getString("contrasenia"));
            cliente.setEstado(clienteObtenido.getBoolean("estado"));
            listaClientes.add(cliente);
        }
        return listaClientes;
    }
}
