package peca;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tinta.Tinta;

public class PecaDAO {
 
    private static final Logger LOGGER = Logger.getLogger(PecaDAO.class.getName());
    private Connection connection;

    public PecaDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha na conexão com o database.", e);
        }
    }


   public void insertPeca(Peca peca) {
    String sql = "INSERT INTO peca (tinta_cor, modelo, area_pintura, qtd_estoque, id) VALUES (?, ?, ?, ?, ?);";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, peca.getTinta_cor().getCor());
        stmt.setString(2, peca.getModelo());
        stmt.setFloat(3, peca.getArea_pintura());
        stmt.setFloat(4, peca.getQtd_estoque());
        stmt.setInt(5, peca.getId());

        // Insert
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the connection
        closeConnection();
    }
}

// DELETE
public void deletePeca(String tinta_cor) {
    // String sql to delete from the database
    String sql = "DELETE FROM peca WHERE tinta_cor = ?;";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Set tinta_cor in place of '?'
        stmt.setString(1, tinta_cor);

        // DELETE
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the connection
        closeConnection();
    }
}

// Method to close the connection
private void closeConnection() {
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

             //UPDATE
    public void updatePeca(String oldCor, Tinta newTinta){
            //String sql para atualizar cadastro do banco de dados
        String sql = "UPDATE FROM peca SET cor = ?, textura = ? WHERE cor = ?;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            //Setando Atributos no lugar de '?'
            stmt.setString(1, newTinta.getCor());
            stmt.setString(2, newTinta.getTextura());
            
            //PK
            stmt.setString(3, oldCor);
            
            //UPDATE
            stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeConnection(); //Encerra a conexão
        }
    }

    //SELECT ALL
    public List<Peca> selectPeca(){
        //String sql para a consulta
        String sql = "SELECT * FROM peca;";
        //Lista de Peça
        List<Peca> listPeca = new ArrayList<>();
        
        try(PreparedStatement stmt = connection.prepareStatement(sql);
            //Select
            ResultSet rs = stmt.executeQuery();){
            
            //Percorrendo lista
            while(rs.next()){
                //Inserindo Peça na lista
                listPeca.add(new Peca(new Tinta(rs.getString("cor"),
                                                rs.getString("textura"),
                                                rs.getFloat("volume")),
                                        rs.getString("modelo"),
                                        rs.getFloat("area_pintura"),
                                        rs.getInt("qtd_estoque"),
                                        rs.getInt("id")));
                
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(); //Encerra a conexão
        }
        
        //Retorna a lista com os resultados
        return listPeca;
    }
}