import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends EmployeeDatabaseOperations {

    // Constructor
    public EmployeeDAO() {
        super();
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        String query = "INSERT INTO employees (empId, name, ssn, jobTitle, division, salary) VALUES (?, ?, ?, ?, ?, ?)";
        executeUpdate(query, newEmployee.getEmpId(), newEmployee.getName(), newEmployee.getSsn(),
                newEmployee.getJobTitle(), newEmployee.getDivision(), newEmployee.getSalary());
    }

    @Override
    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET name = ?, ssn = ?, jobTitle = ?, division = ?, salary = ? WHERE empId = ?";
        executeUpdate(query, employee.getName(), employee.getSsn(), employee.getJobTitle(), employee.getDivision(),
                employee.getSalary(), employee.getEmpId());
    }

    @Override
    public void deleteEmployee(int empId) {
        String query = "DELETE FROM employees WHERE empId = ?";
        executeUpdate(query, empId);
    }

    @Override
    public Employee searchEmployeeById(int empId) {
        String query = "SELECT * FROM employees WHERE empId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, empId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(resultSet.getInt("empId"), resultSet.getString("name"), resultSet.getString("ssn"),
                        resultSet.getString("jobTitle"), resultSet.getString("division"), resultSet.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if not found
    }

    @Override
    public List<Employee> searchEmployeeByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE name LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("empId"), resultSet.getString("name"), resultSet.getString("ssn"),
                        resultSet.getString("jobTitle"), resultSet.getString("division"), resultSet.getDouble("salary")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesInSalaryRange(double minSalary, double maxSalary) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE salary BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, minSalary);
            preparedStatement.setDouble(2, maxSalary);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getInt("empId"), resultSet.getString("name"), resultSet.getString("ssn"),
                        resultSet.getString("jobTitle"), resultSet.getString("division"), resultSet.getDouble("salary")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
