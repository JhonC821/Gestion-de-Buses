/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jonat
 */
public class Conexion {
    private static final String IP = "localhost";
    private static final int PUERTO = 3308;
    private static final String SCHEMA = "gestion_buses_db";
    private static final String USER_NAME = "dev_adm";
    private static final String PASSWORD = "123456";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static Conexion instancia;

    private Connection conexionActual;


    public static Conexion getInstance() throws SQLException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() throws SQLException  {
        return conexionActual = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
     
}
