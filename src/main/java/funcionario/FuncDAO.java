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
        String sql = "INSERT INTO funcionarios (id, nome, cargo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getCargo());
            stmt.executeUpdate();
            System.out.println("Funcionario inserido: " + funcionario);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao inserir funcionário.", e);
        }
    }

    public void deletarFuncionario(int id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Funcionario deletado com id: " + id);
            } else {
                System.out.println("Funcionario com id " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao deletar funcionário.", e);
        }
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome = ?, cargo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Funcionario atualizado: " + funcionario);
            } else {
                System.out.println("Funcionario com id " + funcionario.getId() + " não encontrado.");
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
                Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("cargo"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha ao listar funcionários.", e);
        }
        return funcionarios;
    }

    public Funcionario selecionarFuncionario(int id) {
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("cargo"));
                } else {
                    System.out.println("Funcionario com id " + id + " não encontrado.");
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

    private static class Funcionario {

        public Funcionario(int aInt, String string, String string1) {
        }

        private int getId() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private String getNome() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private String getCargo() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
