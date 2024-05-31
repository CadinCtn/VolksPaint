package funcionario;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncDAO {
 
    private static final Logger LOGGER = Logger.getLogger(FuncDAO.class.getName());
    private Connection connection;

    public FuncDAO() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Falha na conexão com o database.", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}