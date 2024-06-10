package peca;

import dao.DAO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PecaDAO extends DAO<Peca> {

    //INSERT
    @Override
    public void insert(Peca peca) throws SQLException{
        String sql = "INSERT INTO peca (modelo, area_pintura, qtd_estoque) VALUES (?, ?, ?);";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
             stmt.setString(1, peca.getModelo());
             stmt.setFloat(2, peca.getArea_pintura());
             stmt.setFloat(3, peca.getQtd_estoque());

             // Insert
             stmt.executeUpdate();

        }
    }

    // DELETE
    @Override
    public void delete(int id) throws SQLException{
        // String sql to delete from the database
        String sql = "DELETE FROM peca WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            // Set id in place of '?'
            stmt.setInt(1, id);

            // DELETE
            stmt.executeUpdate();
        }
    }

    //UPDATE
    @Override
    public void update(Peca peca) throws SQLException{
        //String sql para atualizar cadastro do banco de dados
        String sql = "UPDATE peca SET modelo = ?, area_pintura = ? WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            //Setando Atributos no lugar de '?'
            stmt.setString(1, peca.getModelo());
            stmt.setFloat(2, peca.getArea_pintura());

            //PK
            stmt.setInt(3, peca.getId());

            //UPDATE
            stmt.executeUpdate();

        }
    }

    //SELECT ALL
    @Override
    public List<Peca> readAll() throws SQLException{
        //String sql para a consulta
        String sql = "SELECT * FROM peca;";
        //Lista de Peça
        List<Peca> listPeca = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql); //Select
                 ResultSet rs = stmt.executeQuery();) {

            //Percorrendo lista
            while (rs.next()) {
                //Inserindo Peça na lista
                listPeca.add(new Peca(rs.getString("modelo"),
                        rs.getFloat("area_pintura"),
                        rs.getInt("qtd_estoque"),
                        rs.getInt("id")));
            }

        }

        //Retorna a lista com os resultados
        return listPeca;
    }

    //SELECT BY ID
    @Override
    public Peca read(int id) throws SQLException{
        //String sql para a consulta
        String sql = "SELECT * FROM peca WHERE id = ?;";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
             stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Peca(rs.getString("modelo"), rs.getFloat("area_pintura"), rs.getInt("qtd_estoque"), rs.getInt("id"));
                }
            }
        } 

        return new Peca(null, 0, 0, 0);
    }

}
