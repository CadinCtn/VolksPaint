package funcionario;

import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDAO {
 
    private Connection connection;

    public FuncionarioDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Método para encerrar a conexão
    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //INSERT
    public void insertFuncionario(Funcionario funcionario){
        String sql = "INSERT INTO funcionario (cpf,nome,senha,data_nascimento) VALUES (?,?,?,?);";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            //Setando atributos
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getSenha());
            stmt.setDate(4, funcionario.getDataNascimento());
            
            //INSERT
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o funcionario!\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
        } finally {
            //Fecha conexão
            closeConnection();
        }
    }

    //DELETE
    public void deleteFuncionario(String cpf){
        String sql = "DELETE FROM funcionario WHERE cpf = ?";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,cpf);
            
            //Delete
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso!");
            
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Não foi possivel deletar o funcionario!\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }finally{
            //Fecha conexão
            closeConnection();
        }
    }
    
    //UPDATE
    public void updateFuncionario(Funcionario funcionario, String oldCpf){
        String sql = "UPDATE funcionario SET cpf = ?, nome = ?, data_nascimento = ?, senha = ? WHERE cpf = ?";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            
            //SET
            stmt.setString(1,funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setDate(3, funcionario.getDataNascimento());
            stmt.setString(4,funcionario.getSenha());
            //WHERE
            stmt.setString(5, oldCpf);
            
            //UPDATE
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Funcionario Atualizado com sucesso!");
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel atualizar o funcionario!\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            //Fecha a conexão
             closeConnection();
        }
    }
    
    //SELECT
    public List<Funcionario> selectFuncionarios(){
        String sql = "SELECT * FROM funcionario";
        
        List<Funcionario> list = new ArrayList<>();
        try(PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            //Percorre a lista de resultados
            while(rs.next()){
                list.add(new Funcionario(rs.getString("cpf"), rs.getString("nome"), rs.getString("senha"),rs.getDate("data_nascimento")));
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    
    
    //SELECT BY CPF
    public Funcionario selectByCpf(String cpf){
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, cpf);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Funcionario(rs.getString("cpf"), rs.getString("nome"), rs.getString("senha"),rs.getDate("data_nascimento"));
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o funcionario.");
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o funcionario!\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    
    
    public boolean login(String cpf, String senha){
        String sql = "SELECT cpf,senha FROM funcionario WHERE cpf = ? AND senha = ?";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return true;
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o funcionario!\nERRO: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }
    
}
