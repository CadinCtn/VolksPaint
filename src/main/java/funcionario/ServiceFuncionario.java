/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionario;

import java.math.BigInteger;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ServiceFuncionario {
    
    //Validar login
    public boolean login(String cpf, String senha){
        return new FuncionarioDAO().login(cpf, senha);
    }
    
    //Inserir funcionario
    public void insertFuncionario(String cpf, String nome, String senha, Date data){
        new FuncionarioDAO().insertFuncionario(new Funcionario(cpf,nome,senha,new java.sql.Date(data.getTime())));
    }
    
    //deletar funcionario
    public void deleteFuncionario(String cpf){
        new FuncionarioDAO().deleteFuncionario(cpf);
    }
    
    //atualizar funcionario
    public void updateFuncionario(String cpf, String nome, String senha, Date data, String oldCpf){
        new FuncionarioDAO().updateFuncionario(new Funcionario(cpf,nome,senha,new java.sql.Date(data.getTime())), oldCpf);
    }

    //select by cpf
    public Funcionario selectFuncionarioByCpf(String cpf){
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
