import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static EmployeeService employeeService = new EmployeeService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Employee Management System ---");
        System.out.println("1. Add Employee");
        System.out.println("2. Search Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Apply Salary Increase");
        System.out.println("0. Exit");
        System.out.print("Please enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice >= 0 && choice <= 5) {
                    break; // Valid choice
                } else {
                    System.out.print("Invalid choice. Please enter a number between 0 and 5: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                addEmployee();
                break;
            case 2:
                searchEmployee();
                break;
            case 3:
                updateEmployee();
                break;
            case 4:
                deleteEmployee();
                break;
            case 5:
                applySalaryIncrease();
                break;
            case 0:
                System.out.println("Exiting the application. Goodbye!");
                System.exit(0);
                break;
        }
    }

    private static void addEmployee() {
        System.out.println("Adding new employee...");
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter SSN (no dashes): ");
        String ssn = scanner.nextLine();

        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();

        System.out.print("Enter Division: ");
        String division = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        Employee newEmployee = new Employee(empId, name, ssn, jobTitle, division, salary);
        employeeDAO.addEmployee(newEmployee); // Assuming addEmployee method exists in EmployeeDAO
        System.out.println("Employee added successfully.");
    }

    private static void searchEmployee() {
        System.out.println("Searching for employee...");
        System.out.print("Enter Employee ID, Name, or SSN: ");
        scanner.nextLine(); // Consume newline
        String searchInput = scanner.nextLine();

        Employee employee = employeeDAO.searchEmployeeById(Integer.parseInt(searchInput)); // Attempt to search by ID
                                                                                           // first

        if (employee == null) {
            List<Employee> employeesByName = employeeDAO.searchEmployeeByName(searchInput);
            if (!employeesByName.isEmpty()) {
                for (Employee emp : employeesByName) {
                    System.out.println(emp);
                }
            } else {
                System.out.println("No employee found with the provided details.");
            }
        } else {
            System.out.println(employee); // Print employee details if found
        }
    }

    private static void updateEmployee() {
        System.out.println("Updating employee...");
        System.out.print("Enter Employee ID to update: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Employee employee = employeeDAO.searchEmployeeById(empId);
        if (employee == null) {
            System.out.println("No employee found with ID: " + empId);
            return;
        }

        System.out.println("Current details: " + employee);
        System.out.print("Enter new Name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty())
            employee.setName(name);

        System.out.print("Enter new SSN (leave blank to keep current): ");
        String ssn = scanner.nextLine();
        if (!ssn.isEmpty())
            employee.setSsn(ssn);

        System.out.print("Enter new Job Title (leave blank to keep current): ");
        String jobTitle = scanner.nextLine();
        if (!jobTitle.isEmpty())
            employee.setJobTitle(jobTitle);

        System.out.print("Enter new Division (leave blank to keep current): ");
        String division = scanner.nextLine();
        if (!division.isEmpty())
            employee.setDivision(division);

        System.out.print("Enter new Salary (leave blank to keep current): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty())
            employee.setSalary(Double.parseDouble(salaryInput));

        employeeDAO.updateEmployee(employee);
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee() {
        System.out.println("Deleting employee...");
        System.out.print("Enter Employee ID to delete: ");
        int empId = scanner.nextInt();

        Employee employee = employeeDAO.searchEmployeeById(empId);
        if (employee != null) {
            employeeDAO.deleteEmployee(empId); // Assuming deleteEmployee method exists in EmployeeDAO
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("No employee found with ID: " + empId);
        }
    }

    private static void applySalaryIncrease() {
        System.out.println("Applying salary increase...");
        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();
        System.out.print("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();
        System.out.print("Enter percentage increase (e.g., for 3.2% enter 3.2): ");
        double percentage = scanner.nextDouble();

        List<Employee> employees = employeeDAO.getEmployeesInSalaryRange(minSalary, maxSalary);
        for (Employee emp : employees) {
            double newSalary = emp.getSalary() + (emp.getSalary() * (percentage / 100));
            emp.setSalary(newSalary);
            employeeDAO.updateEmployee(emp); // Update employee with new s
        }
    }
}