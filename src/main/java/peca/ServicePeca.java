/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peca;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ServicePeca extends PecaDAO{
    
    
    //Inserir peca
    @Override
    public void insertPeca(Peca peca){
        super.insertPeca(peca);
    }
    
    //Update peca
    @Override
    public void updatePeca(Peca peca){
        super.updatePeca(peca);
    }
    
    //Deletar peca
    @Override
    public void deletePeca(int id){
        super.deletePeca(id);
    }
    
    //Select By id
    @Override
    public Peca selectPecaByID(int id){
        return super.selectPecaByID(id);
    }
    
    
    //Preencher a tabela de pecas
    public void tabelaPecas(JTable table){
        //Modelo da tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        //limpando a tabela
        model.setRowCount(0);
        
        //Adicionando valores a tabela
        for(Peca peca : super.selectPeca()){
            model.addRow(peca.pecaInTable());
        }
    }
    
}
