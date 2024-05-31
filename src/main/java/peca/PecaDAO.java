package peca;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PecaDAO {
 
    private static final Logger LOGGER = Logger.getLogger(PecaDAO.class.getName());
    private Connection connection;

    public PecaDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha na conecção com o database.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}