import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    // Method to search an employee by ID
    public Employee searchEmployeeById(int empId) {
        Employee employee = null;
        String query = "SELECT * FROM employees WHERE empId = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, empId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                // Assuming the columns are in the order: empId, name, ssn, jobTitle, division, salary
                employee = new Employee(
                    resultSet.getInt("empId"),
                    resultSet.getString("name"),
                    resultSet.getString("ssn"),
                    resultSet.getString("jobTitle"),
                    resultSet.getString("division"),
                    resultSet.getDouble("salary")
                );
            }
        } catch (SQLException e) {
            System.out.println("Failed to search employee by ID.");
            e.printStackTrace();
        }
        
        return employee; // Will be null if not found
    }

    // Method to search employees by name
    public List<Employee> searchEmployeeByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE name LIKE ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Employee employee = new Employee(
                    resultSet.getInt("empId"),
                    resultSet.getString("name"),
                    resultSet.getString("ssn"),
                    resultSet.getString("jobTitle"),
                    resultSet.getString("division"),
                    resultSet.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println("Failed to search employee by name.");
            e.printStackTrace();
        }
        
        return employees; // Will return an empty list if no matches are found
    }

    public Employee searchEmployeeBySsn(String ssn) {
        String query = "SELECT * FROM employees WHERE ssn = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             preparedStatement.setString(1, ssn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("empId"),
                        resultSet.getString("name"),
                        resultSet.getString("ssn"),
                        resultSet.getString("jobTitle"),
                        resultSet.getString("division"),
                        resultSet.getDouble("salary")
                );
            }
        } catch (SQLException e) {
            System.out.println("Failed to search employee by SSN.");
            e.printStackTrace();
        }
        return null;
    }

    // Method to get employees in a specified salary range
    public List<Employee> getEmployeesInSalaryRange(double minSalary, double maxSalary) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE salary BETWEEN ? AND ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setDouble(1, minSalary);
            preparedStatement.setDouble(2, maxSalary);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Employee employee = new Employee(
                    resultSet.getInt("empId"),
                    resultSet.getString("name"),
                    resultSet.getString("ssn"),
                    resultSet.getString("jobTitle"),
                    resultSet.getString("division"),
                    resultSet.getDouble("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve employees by salary range.");
            e.printStackTrace();
        }
        
        return employees; // Returns list of employees in the salary range
    }

    // Method to update an existing employee's details
    public void updateEmployee(Employee emp) {
        String query = "UPDATE employees SET name = ?, ssn = ?, jobTitle = ?, division = ?, salary = ? WHERE empId = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getSsn());
            preparedStatement.setString(3, emp.getJobTitle());
            preparedStatement.setString(4, emp.getDivision());
            preparedStatement.setDouble(5, emp.getSalary());
            preparedStatement.setInt(6, emp.getEmpId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update employee.");
            e.printStackTrace();
        }
    }

    // Method to delete an employee from the database
    public void deleteEmployee(int empId) {
        String query = "DELETE FROM employees WHERE empId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, empId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee with ID " + empId + " deleted successfully.");
            } else {
                System.out.println("No employee found with ID " + empId + ".");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete employee.");
            e.printStackTrace();
        }
    }

    // Method to add a new employee to the database
    public void addEmployee(Employee newEmployee) {
        String query = "INSERT INTO employees (empId, name, ssn, jobTitle, division, salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, newEmployee.getEmpId());
            preparedStatement.setString(2, newEmployee.getName());
            preparedStatement.setString(3, newEmployee.getSsn());
            preparedStatement.setString(4, newEmployee.getJobTitle());
            preparedStatement.setString(5, newEmployee.getDivision());
            preparedStatement.setDouble(6, newEmployee.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Failed to add employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
        