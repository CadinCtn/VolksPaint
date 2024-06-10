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
import javax.swing.JOptionPane;

public abstract class PecaDAO {
 
    private static final Logger LOGGER = Logger.getLogger(PecaDAO.class.getName());
    private Connection connection;

    public PecaDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha na conexão com o database.", e);
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
    
   //INSERT
   public void insertPeca(Peca peca) {
    String sql = "INSERT INTO peca (modelo, area_pintura, qtd_estoque) VALUES (?, ?, ?);";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, peca.getModelo());
        stmt.setFloat(2, peca.getArea_pintura());
        stmt.setFloat(3, peca.getQtd_estoque());

        // Insert
        stmt.executeUpdate();
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Não foi possivel inserir a Peça.\nERRO: " + e.getMessage(), "ERRO", JOptionPane.WARNING_MESSAGE);
        e.printStackTrace();
    } finally {
        // Close the connection
        closeConnection();
    }
}

// DELETE
public void deletePeca(int id) {
    // String sql to delete from the database
    String sql = "DELETE FROM peca WHERE id = ?;";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Set id in place of '?'
        stmt.setInt(1, id);

        // DELETE
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the connection
        closeConnection();
    }
}

     //UPDATE
    public void updatePeca(Peca peca){
        //String sql para atualizar cadastro do banco de dados
        String sql = "UPDATE peca SET modelo = ?, area_pintura = ? WHERE id = ?;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            //Setando Atributos no lugar de '?'
            stmt.setString(1, peca.getModelo());
            stmt.setFloat(2, peca.getArea_pintura());
            
            //PK
            stmt.setInt(3, peca.getId());
            
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
                listPeca.add(new Peca(rs.getString("modelo"),
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

    //SELECT BY ID
    public Peca selectPecaByID(int id){
        //String sql para a consulta
        String sql = "SELECT * FROM peca WHERE id = ?;";
        
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                   return new Peca(rs.getString("modelo"),rs.getFloat("area_pintura"),rs.getInt("qtd_estoque"),rs.getInt("id")); 
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return new Peca(null, 0, 0, 0);
    }



}