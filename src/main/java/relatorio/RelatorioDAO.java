/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    public void insertRelatorio(Relatorio relatorio, int turno){
        String sql = "INSERT INTO relatorio (data_relatorio, linha_producao, turno, consumo_tinta, qtd_pecas, consumo_unidade, limite_consumo, desperdicio_tinta) VALUES (?,?,?,?,?,?,?,?) "; // Terminar a String SQL
        
        try(PreparedStatement stmt = connection.prepareStatement(sql);){
            //Chaves
            stmt.setDate(1, relatorio.data);
            stmt.setInt(2, relatorio.linhaProducao);
            stmt.setInt(3, turno);
            //Valores
            stmt.setFloat(4, relatorio.getConsumoTinta(turno));
            stmt.setInt(5, relatorio.getQtdPecas(turno));
            stmt.setFloat(5, relatorio.getConsumoUnidades(turno));
            stmt.setFloat(6, relatorio.getLimiteConsumoUnidade(turno));
            stmt.setFloat(7, relatorio.getDesperdicioTinta(turno));
            
            //INSERT
            stmt.executeUpdate();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o relatório.\n" + e.getMessage(),"AVISO",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    //DELETE
    public void deleteRelatorio(Relatorio relatorio){
        String sql = "DELETE relatorio WHERE data_relatorio = ? AND linha_producao = ?;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, relatorio.data);
            stmt.setInt(2, relatorio.linhaProducao);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel deletar o relatorio.\n" + e.getMessage(),"AVISO",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
}
