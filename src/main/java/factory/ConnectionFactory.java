/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {

    public Connection getConnection() throws SQLException {
        // Faz a conexão com o banco
        return DriverManager.getConnection("jdbc:mysql://10.80.49.81/volkspaint", "root", "root");
    }
}
