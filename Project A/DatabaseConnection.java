import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Initialize connection (replace with your actual DB credentials)
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeData", "user", "password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
