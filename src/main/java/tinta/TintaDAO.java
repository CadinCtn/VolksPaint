/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinta;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class TintaDAO {
 
    private Connection connection;
    public TintaDAO(){
        try{
            this.connection = new ConnectionFactory().getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    
    
    
}
