import java.sql.*;
import java.util.Scanner;

public class EmployeeManagement {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/ProjectA";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";
    private static Connection connection;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Establish connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            addSSNColumn();

            while (true) {
                System.out.println("\nEmployee Management System");
                System.out.println("1. Add Employee");
                System.out.println("2. Update Employee");
                System.out.println("3. Search Employee");
                System.out.println("4. Increase Salary by Range");
                System.out.println("5. View Reports");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        addEmployee(scanner);
                        break;
                    case "2":
                        updateEmployee(scanner);
                        break;
                    case "3":
                        searchEmployee(scanner);
                        break;
                    case "4":
                        updateSalaryIncrease(scanner);
                        break;
                    case "5":
                        viewReports(scanner);
                        break;
                    case "6":
                        System.out.println("Exiting Employee Management System.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }

    private static void addSSNColumn() {
        try (Statement statement = connection.createStatement()) {
            String query = "ALTER TABLE employees ADD COLUMN IF NOT EXISTS ssn VARCHAR(9);";
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Error adding SSN column: " + e.getMessage());
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine();
        System.out.print("Division: ");
        String division = scanner.nextLine();
        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.print("SSN (no dashes): ");
        String ssn = scanner.nextLine();

        String query = "INSERT INTO employees (name, job_title, division, salary, ssn) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, jobTitle);
            statement.setString(3, division);
            statement.setDouble(4, salary);
            statement.setString(5, ssn);
            statement.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Employee ID: ");
        int empId = Integer.parseInt(scanner.nextLine());
        System.out.print("Field to update (name, job_title, division, salary, ssn): ");
        String field = scanner.nextLine();
        System.out.print("New Value: ");
        String newValue = scanner.nextLine();

        String query = "UPDATE employees SET " + field + " = ? WHERE empid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newValue);
            statement.setInt(2, empId);
            statement.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    private static void searchEmployee(Scanner scanner) {
        System.out.print("Search by (name, ssn, empid): ");
        String identifier = scanner.nextLine();
        System.out.print("Enter value to search: ");
        String searchValue = scanner.nextLine();

        String query = "SELECT * FROM employees WHERE " + identifier + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, searchValue);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("ID: %d, Name: %s, Job Title: %s, Division: %s, Salary: %.2f, SSN: %s%n",
                        resultSet.getInt("empid"),
                        resultSet.getString("name"),
                        resultSet.getString("job_title"),
                        resultSet.getString("division"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("ssn"));
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee: " + e.getMessage());
        }
    }

    private static void updateSalaryIncrease(Scanner scanner) {
        System.out.print("Increase Percentage: ");
        double percent = Double.parseDouble(scanner.nextLine());
        System.out.print("Minimum Salary: ");
        double minSalary = Double.parseDouble(scanner.nextLine());
        System.out.print("Maximum Salary: ");
        double maxSalary = Double.parseDouble(scanner.nextLine());

        String query = "UPDATE employees SET salary = salary * (1 + ? / 100) WHERE salary BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, percent);
            statement.setDouble(2, minSalary);
            statement.setDouble(3, maxSalary);
            statement.executeUpdate();
            System.out.println("Salaries updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating salaries: " + e.getMessage());
        }
    }

    private static void viewReports(Scanner scanner) {
        System.out.println("1. Employee Pay History");
        System.out.println("2. Total Pay by Job Title");
        System.out.println("3. Total Pay by Division");
        System.out.print("Choose a report: ");
        String reportChoice = scanner.nextLine();

        switch (reportChoice) {
            case "1":
                getEmployeePayHistory();
                break;
            case "2":
                getTotalPayByJobTitle();
                break;
            case "3":
                getTotalPayByDivision();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void getEmployeePayHistory() {
        String query = "SELECT e.*, p.pay_date, p.amount " +
                       "FROM employees e " +
                       "LEFT JOIN pay_statements p ON e.empid = p.empid";
        try (Statement statement = connection.createStatement(); 
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.printf("ID: %d, Name: %s, Pay Date: %s, Amount: %.2f%n",
                        resultSet.getInt("empid"),
                        resultSet.getString("name"),
                        resultSet.getDate("pay_date"),
                        resultSet.getDouble("amount"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employee pay history: " + e.getMessage());
        }
    }

    private static void getTotalPayByJobTitle() {
        String query = "SELECT job_title, SUM(amount) AS total_pay " +
                       "FROM employees e " +
                       "JOIN pay_statements p ON e.empid = p.empid " +
                       "WHERE MONTH(p.pay_date) = MONTH(CURDATE()) " +
                       "GROUP BY job_title";
        try (Statement statement = connection.createStatement(); 
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.printf("Job Title: %s, Total Pay: %.2f%n",
                        resultSet.getString("job_title"),
                        resultSet.getDouble("total_pay"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total pay by job title: " + e.getMessage());
        }
    }

    private static void getTotalPayByDivision() {
        String query = "SELECT division, SUM(amount) AS total_pay " +
                       "FROM employees e " +
                       "JOIN pay_statements p ON e.empid = p.empid " +
                       "WHERE MONTH(p.pay_date) = MONTH(CURDATE()) " +
                       "GROUP BY division";
        try (Statement statement = connection.createStatement(); 
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                System.out.printf("Division: %s, Total Pay: %.2f%n",
                        resultSet.getString("division"),
                        resultSet.getDouble("total_pay"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching total pay by division: " + e.getMessage());
        }
    }
}
