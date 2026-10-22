import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final int LOGIN_TIMEOUT_SECONDS = 5;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "pass";

    public Connection connect() throws SQLException {
        DriverManager.setLoginTimeout(LOGIN_TIMEOUT_SECONDS);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}