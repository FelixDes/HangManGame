package HangMan.db.connector;

import org.di.annotations.Component;
import org.di.annotations.Singleton;

import java.sql.*;

@Singleton
@Component
public class DBControllerH2 implements DBController {
    private final Connection connection;
    private final String url = "jdbc:h2:tcp://localhost/~/test";
    private final String login = "sa";
    private final String password = "";

    public DBControllerH2() throws SQLException {
        connection = DriverManager.getConnection(url, login, password);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        var stm = connection.createStatement();
        return stm.executeQuery(query);
    }

    public PreparedStatement prepareStatement(String stt) throws SQLException {
        return connection.prepareStatement(stt);
    }
}
