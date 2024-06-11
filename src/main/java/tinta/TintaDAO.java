/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tinta;

import dao.DAO;
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
public class TintaDAO extends DAO<Tinta> {

    //INSERT
    @Override
    public void insert(Tinta tinta) throws SQLException {
        String sql = "INSERT INTO tinta (cor, textura, qtd_estoque) VALUES (?, ?, ?);";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, tinta.getCor());
            stmt.setString(2, tinta.getTextura());
            stmt.setFloat(3, tinta.getQtdEstoque());

            // Insert
            stmt.executeUpdate();

        }
    }

    // DELETE
    @Override
    public void delete(int id) throws SQLException {
        // String sql to delete from the database
        String sql = "DELETE FROM tinta WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            // Set id in place of '?'
            stmt.setInt(1, id);

            // DELETE
            stmt.executeUpdate();
        }
    }

    //UPDATE
    @Override
    public void update(Tinta tinta) throws SQLException {
        //String sql para atualizar cadastro do banco de dados
        String sql = "UPDATE tinta SET cor = ?, textura = ? WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            //Setando Atributos no lugar de '?'
            stmt.setString(1, tinta.getCor());
            stmt.setString(2, tinta.getTextura());

            //PK
            stmt.setInt(3, tinta.getId());

            //UPDATE
            stmt.executeUpdate();

        }
    }

    //SELECT ALL
    @Override
    public List<Tinta> readAll() throws SQLException {
        //String sql para a consulta
        String sql = "SELECT * FROM tinta;";
        //Lista de Tinta
        List<Tinta> listTinta = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); //Select
                 ResultSet rs = stmt.executeQuery();) {

            //Percorrendo lista
            while (rs.next()) {
                //Inserindo Tinta na lista
                listTinta.add(new Tinta(rs.getInt("id"),
                        rs.getString("cor"),
                        rs.getString("textura"),
                        rs.getFloat("qtd_estoque")));
            }

        }

        //Retorna a lista com os resultados
        return listTinta;
    }

    //SELECT BY ID
    @Override
    public Tinta read(int id) throws SQLException {
        //String sql para a consulta
        String sql = "SELECT * FROM tinta WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tinta(rs.getInt("id"), rs.getString("cor"), rs.getString("textura"), rs.getFloat("qtd_estoque"));
                }
            }
        }

        return new Tinta(0, null, null, 0);
    }

}
