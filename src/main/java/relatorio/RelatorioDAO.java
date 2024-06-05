/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class RelatorioDAO {
    
    
    private Connection connection;
    
    public RelatorioDAO(){
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void closeConnection(){
        try{
            if(!this.connection.isClosed()){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }    
    
    //INSERT
    public void insertRelatorio(Relatorio relatorio){
        String sql = "INSERT INTO relatorio"; // Terminar a String SQL
        
        try(PreparedStatement stmt = connection.prepareStatement(sql);){
            
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            
        }
        
        
    }



    
    
}
