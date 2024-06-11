/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinta;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    //Preencher a tabela de tintas
    public void tabelaPecas(JTable table) {
        //Modelo da tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        //limpando a tabela
        model.setRowCount(0);

        try {
            //Adicionando valores a tabela
            for (Tinta tinta : dao.readAll()) {
                model.addRow(tinta.tintaInTable());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar a tabela.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

}
