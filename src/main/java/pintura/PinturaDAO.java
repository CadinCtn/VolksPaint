/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pintura;

import dao.DAO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tinta.Tinta;

/**
 *
 * @author Senai
 */
public class PinturaDAO extends DAO<Pintura> {

    @Override
    public void insert(Pintura pintura) throws SQLException {
        String sql = "INSERT INTO pintura (data_pintura, linha_producao, turno, funcionario, peca, tinta, consumo_unidade, limite_consumo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setDate(1, pintura.getData());
            stmt.setInt(2, pintura.getLinhaProducao());
            stmt.setInt(3, pintura.getTurno());
            stmt.setString(4, pintura.getFuncionario());
            stmt.setInt(5, pintura.getPeca());
            stmt.setInt(6, pintura.getTinta());
            stmt.setFloat(7, pintura.getConsumoTintaUnidade());
            stmt.setFloat(8, pintura.getLimiteConsumoTinta());

            // Insert
            stmt.executeUpdate();

        }
    }

    @Override
    public Pintura read(int id) throws SQLException {
        //String sql para a consulta
        String sql = "SELECT * FROM pintura WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pintura(rs.getInt("id"), rs.getDate("data_pintura"), rs.getInt("linha_producao"), rs.getInt("turno"), rs.getString("funcionario"), rs.getInt("peca"), rs.getInt("tinta"), rs.getFloat("consumo_unidade"), rs.getFloat("limite_consumo"));
                }
            }
        }

        return new Pintura(0, null, 0, 0, null, 0, 0, 0, 0);
    }

    @Override
    public List<Pintura> readAll() throws SQLException {
         //String sql para a consulta
        String sql = "SELECT * FROM tinta;";
        //Lista de Tinta
        List<Pintura> listPintura = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); //Select
                 ResultSet rs = stmt.executeQuery();) {

            //Percorrendo lista
            while (rs.next()) {
                //Inserindo Tinta na lista
                listPintura.add(new Pintura(rs.getInt("id"), rs.getDate("data_pintura"), rs.getInt("linha_producao"), rs.getInt("turno"), rs.getString("funcionario"), rs.getInt("peca"), rs.getInt("tinta"), rs.getFloat("consumo_unidade"), rs.getFloat("limite_consumo")));
            }

        }

        //Retorna a lista com os resultados
        return listPintura;
    }

    @Override
    public void update(Pintura pintura) throws SQLException {
        //String sql para atualizar cadastro do banco de dados
        String sql = "UPDATE pintura SET data_pintura = ?, linha_producao = ?, turno = ?, funcionario = ?, peca = ?, tinta = ?, consumo_unidade = ?, limite_consumo = ?  WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            //Setando Atributos no lugar de '?'
            stmt.setDate(1, pintura.getData());
            stmt.setInt(2, pintura.getLinhaProducao());
            stmt.setInt(3, pintura.getTurno());
            stmt.setString(4, pintura.getFuncionario());
            stmt.setInt(5, pintura.getPeca());
            stmt.setInt(6, pintura.getTinta());
            stmt.setFloat(7, pintura.getConsumoTintaUnidade());
            stmt.setFloat(8, pintura.getLimiteConsumoTinta());

            // Insert
            stmt.executeUpdate();

            //PK
            stmt.setInt(9, pintura.getId());

            //UPDATE
            stmt.executeUpdate();

        }
    }

    @Override
    public void delete(int id) throws SQLException {
        // String sql to delete from the database
        String sql = "DELETE FROM pintura WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {
            // Set id in place of '?'
            stmt.setInt(1, id);

            // DELETE
            stmt.executeUpdate();
        }
    }
    
    
    
}
