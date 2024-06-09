/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionario;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ServiceFuncionario {
    
    //Inserir funcionario
    public void insertFuncionario(Funcionario funcionario){
        new FuncionarioDAO().insertFuncionario(funcionario);
    }
    
    //deletar funcionario
    public void deleteFuncionario(int cpf){
        new FuncionarioDAO().deleteFuncionario(cpf);
    }
    
    //atualizar funcionario
    public void updateFuncionario(Funcionario funcionario, int cpf){
        new FuncionarioDAO().updateFuncionario(funcionario, cpf);
    }

    //select by cpf
    public Funcionario selectFuncionarioByCpf(int cpf){
        return new FuncionarioDAO().selectByCpf(cpf);
    }
    
    //gerando tabela
    public void tabelaFuncionario(JTable tabela){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        //Limpando tabela
        model.setRowCount(0);
        //Inserindo dados
        for(Funcionario f : new FuncionarioDAO().selectFuncionarios()){
            Object[] line = {f.getCpf(),f.getNome(),f.getDataNascimento()};
            model.addRow(line);
        }
        
    }
    
}
