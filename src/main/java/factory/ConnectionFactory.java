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
        return DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/volkspaint", "faculdade", "24042005Cgr@");
    }
}