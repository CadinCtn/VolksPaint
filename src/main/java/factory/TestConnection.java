/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class TestConnection {
    
    public static void main(String[] args){
        
        try{
            Connection connection = new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "FUNCIONOU");
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    } 
    
}
