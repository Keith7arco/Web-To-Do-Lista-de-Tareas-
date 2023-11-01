/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author tarco
 */
public class Conexion {
    Connection con = null;
    public Connection Conectar(){
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("tododb");
            dataSource.setUser("root");
            dataSource.setPassword("1234");
            dataSource.setServerTimezone("UTC");

            con = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Err "+e);
        }
        return con;
    }
    
    public void Desconectar(){
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
    }
}
