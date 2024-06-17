/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinta;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import peca.Peca;

/**
 *
 * @author Lenovo
 */
public class ServiceTinta {

    private TintaDAO dao;

    public ServiceTinta() {
        dao = new TintaDAO();
    }

    //INSERT
    public void insertTinta(Tinta tinta) {
        try {
            dao.insert(tinta);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o item.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //UPDATE
    public void updateTinta(Tinta tinta) {
        try {
            dao.update(tinta);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o item.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //DELETE
    public void deleteTinta(int id) {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o item.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    //SELECT BY ID
    public Tinta selectByID(int id) {
        try {
            return dao.read(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao encontrar o item.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    //CHANGE QTD
    public void changeQtdTinta(int value, int id){
       try{
            dao.changeQtdTinta(id, value);
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Erro ao alterar a quantidade o item.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
       }
    }
    
    //Preencher a tabela de tintas
    public void tabelaTintas(JTable table){
        //Modelo da tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        //limpando a tabela
        model.setRowCount(0);
        
        table.setDefaultRenderer(Tinta.class, new CustomTableCellRenderer());
        
        try {
            //Adicionando valores a tabela
            for(Tinta tinta : dao.readAll()){
                model.addRow(tinta.tintaInTable());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar a tabela.\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

class CustomTableCellRenderer extends JLabel implements TableCellRenderer {
    
        public CustomTableCellRenderer() {
            setOpaque(true); // make sure the background is painted
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Customize the background and foreground for specific cell
            if (column == 1 && value!=null) { 
                setBackground(new Color(Integer.parseInt(String.valueOf(value))));
                setForeground(new Color(Integer.parseInt(String.valueOf(value))));
            } else {
                setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            }

            return this;
        }
}
