/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pintura;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Senai
 */
public class ServicePintura {
    
    private PinturaDAO dao;
    
    public ServicePintura() {
        dao = new PinturaDAO();
    }

    //INSERT
    public void insertPintura(Pintura pintura) {
        try {
            dao.insert(pintura);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o pintura.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //UPDATE
    public void updatePintura(Pintura pintura) {
        try {
            dao.update(pintura);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o pintura.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //DELETE
    public void deletePintura(int id) {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o pintura.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //SELECT BY ID
    public Pintura selectByID(int id) {
        try {
            return dao.read(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o pintura.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    //Preencher a tabela de tintas
    public void tabelaPintura(JTable table){
        //Modelo da tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        //limpando a tabela
        model.setRowCount(0);
        
        try {
            //Adicionando valores a tabela
            for(Pintura pintura : dao.readAll()){
                model.addRow(pintura.pinturaInTable());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao gerar a tabela.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
