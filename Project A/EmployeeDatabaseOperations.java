import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class EmployeeDatabaseOperations implements EmployeeOperations {
    protected Connection connection;

    // Constructor to initialize the connection
    public EmployeeDatabaseOperations() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Common method to execute updates (insert, update, delete)
    protected void executeUpdate(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
