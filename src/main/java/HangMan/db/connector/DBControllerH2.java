package HangMan.db.connector;

import org.di.annotations.Component;
import org.di.annotations.Singleton;

import java.sql.*;

@Singleton
@Component
public class DBControllerH2 implements DBController {
    private final Connection connection;

    public DBControllerH2() throws SQLException, ClassNotFoundException {
        String url = "jdbc:h2:tcp://localhost/~/test";
        connection = DriverManager.getConnection(url, "sa", "");
    }

    public ResultSet executeQuery(String query) throws SQLException {
        var stm = connection.createStatement();
        return stm.executeQuery(query);
    }

    public PreparedStatement prepareStatement(String stt) throws SQLException {
        return connection.prepareStatement(stt);
    }
}
