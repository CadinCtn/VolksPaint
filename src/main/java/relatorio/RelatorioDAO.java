/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            connection.close();
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
        } finally {
            closeConnection();
        }
    }
    

    //Select Consumo Tinta
    public List<Relatorio> selectConsumoTinta(Date dataInicio, Date dataFim, int linha){
        //Selecionando Consumo de tintas
        String sql = "SELECT data_relatorio, linha_producao, turno, consumo_tinta FROM relatoprio WHERE data_relatorio BETWEEN ? AND ? AND linha_producao = ?";
        
        List<Relatorio> listRelatorio = new ArrayList<>();
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDate(1, dataInicio);
            stmt.setDate(2, dataFim);
            stmt.setInt(1, linha);
            
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    
                    
                }
            }
            
        }catch(SQLException  e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return listRelatorio;
    }
    
    
}
