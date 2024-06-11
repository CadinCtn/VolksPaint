/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peca;

import dao.DAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ServicePeca extends PecaDAO{
    
    private PecaDAO dao;
    
    public ServicePeca(){
        dao = new PecaDAO();
    }
    
    //INSERT
    public void insertPeca(Peca peca){
        try{
            dao.insert(peca);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o item.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //UPDATE
    public void updatePeca(Peca peca){
        try{
           dao.update(peca);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o item.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //DELETE
    public void deletePeca(int id){
        try{
            dao.delete(id);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao deletar o item.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //SELECT BY ID
    public Peca selectByID(int id){
        try{
            return dao.read(id);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o item.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    //CHANGE QTD
    public void changeQtdPeca(int value, int id){
       try{
            dao.changeQtdPeca(id, value);
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao alterar a quantidade o item.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
       }
    }
    
    //Preencher a tabela de pecas
    public void tabelaPecas(JTable table){
        //Modelo da tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        //limpando a tabela
        model.setRowCount(0);
        
        try {
            //Adicionando valores a tabela
            for(Peca peca : dao.readAll()){
                model.addRow(peca.pecaInTable());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar a tabela.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
