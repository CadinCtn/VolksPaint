/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinta;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public abstract class TintaDAO {

    private Connection connection;

    public TintaDAO() {
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
    public void insertTinta(Tinta tinta) {
        //String sql para inserir no banco de dados
        String sql = "INSERT INTO tinta (cor,textura,volume) VALUES (?,?,?);";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //Setando atributos na String (posição do '?', valor)
            stmt.setString(1, tinta.getCor());
            stmt.setString(2, tinta.getTextura());
            stmt.setFloat(3, tinta.getVolume());

            //Insert
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Encerra a conexão
            closeConnection();
        }
    }

    //DELETE
    public void deleteTinta(String cor) {
        //String sql para deletar do banco de dados
        String sql = "DELETE FROM tinta WHERE cor = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //Setando Cor no lugar de '?'
            stmt.setString(1, cor);

            //DELETE
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(); //encerra conexão
        }
    }

    //UPDATE
    public void updateTinta(String oldCor, Tinta newTinta) {
        //String sql para atualizar cadastro do banco de dados
        String sql = "UPDATE FROM tinta SET cor = ?, textura = ? WHERE cor = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //Setando Atributos no lugar de '?'
            stmt.setString(1, newTinta.getCor());
            stmt.setString(2, newTinta.getTextura());

            //PK
            stmt.setString(3, oldCor);

            //UPDATE
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(); //Encerra a conexão
        }
    }

    //SELECT ALL
    public List<Tinta> selectTinta() {
        //String sql para a consulta
        String sql = "SELECT * FROM tinta;";
        //Lista de Tinta
        List<Tinta> listTinta = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql); //Select
                 ResultSet rs = stmt.executeQuery();) {

            //Percorrendo lista
            while (rs.next()) {
                //Inserindo Tinta na lista
                listTinta.add(new Tinta(rs.getString("cor"),
                        rs.getString("textura"),
                        rs.getFloat("volume")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(); //Encerra a conexão
        }

        //Retorna a lista com os resultados
        return listTinta;
    }

    //Select By Cor
    public Tinta selectTintaByCor(String cor) {
        //String sql para a consulta
        String sql = "SELECT * FROM tinta WHERE cor = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cor);

            //SELECT
            try (ResultSet rs = stmt.executeQuery()) {
                //Se encontrar algum resultado
                if (rs.next()) {
                    //Retorna Tinta
                    return new Tinta(rs.getString("cor"),
                            rs.getString("textura"),
                            rs.getFloat("volume"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        //Retorna nulo se não encontrar nada
        return null;
    }

    //Atualiza volume
    public void changeVolume(String cor, float volume) {
        //String para update
        String sql = "UPDATE FROM tinta SET volume = ? WHERE cor = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, volume);
            stmt.setString(2, cor);

            //UPDATE
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

}
