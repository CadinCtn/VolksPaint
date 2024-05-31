package funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncDAO {
 
    private static final Logger LOGGER = Logger.getLogger(FuncDAO.class.getName());
    private Connection connection;

    public FuncDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "password");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha na conexão com o banco de dados.", e);
        }
    }

    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (CPF, nome, data_nascimento) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            stmt.executeUpdate();
            System.out.println("Funcionario inserido: " + funcionario);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao inserir funcionário.", e);
        }
    }

    public void deletarFuncionario(int CPF) {
        String sql = "DELETE FROM funcionarios WHERE CPF = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, CPF);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Funcionario deletado com CPF: " + CPF);
            } else {
                System.out.println("Funcionario com CPF " + CPF + " não encontrado.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao deletar funcionário.", e);
        }
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome = ?, data_nascimento = ? WHERE CPF = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setDate(2, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setInt(3, funcionario.getCpf());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Funcionario atualizado: " + funcionario);
            } else {
                System.out.println("Funcionario com CPF " + funcionario.getCpf() + " não encontrado.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao atualizar funcionário.", e);
        }
    }

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(rs.getInt("CPF"), rs.getString("nome"), rs.getDate("data_nascimento").toLocalDate());
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao listar funcionários.", e);
        }
        return funcionarios;
    }

    public Funcionario selecionarFuncionario(int CPF) {
        String sql = "SELECT * FROM funcionarios WHERE CPF = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, CPF);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(rs.getInt("CPF"), rs.getString("nome"), rs.getDate("data_nascimento").toLocalDate());
                } else {
                    System.out.println("Funcionario com CPF " + CPF + " não encontrado.");
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao selecionar funcionário.", e);
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
