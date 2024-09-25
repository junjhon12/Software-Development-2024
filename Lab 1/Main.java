import java.util.ArrayList; // Import ArrayList class
import java.util.List;      // Import List interface

// Main class to run the application
public class Main {
    // Entry point of the application
    public static void main(String[] args) {
        // Create lists to hold employees and managers
        List<Employee> employeeList = new ArrayList<>(); // List for Employee objects
        List<Manager> managerList = new ArrayList<>();   // List for Manager objects

        // Create instances of Employee and Manager with the Role enum
        Employee employee1 = new Employee("John Doe", 30, "123 Maple Street", "E12345", "Engineering", 85000.00, Role.ENGINEER);
        Manager manager1 = new Manager("Alice Johnson", 40, "789 Birch Lane", "M67890", "IT", 95000.00, 5, 10000.00, Role.MANAGER);

        // Add the created employee and manager to their respective lists
        employeeList.add(employee1); // Add employee1 to employeeList
        managerList.add(manager1);    // Add manager1 to managerList

        // Print information for the employee and manager
        employee1.printInfo(); // Call printInfo method for employee1
        System.out.println();   // Print a blank line for spacing
        manager1.printInfo();   // Call printInfo method for manager1

        // Create an instance of ReportGenerator to generate reports
        ReportGenerator reportGenerator = new ReportGenerator(employeeList, managerList);
        // Print reports for all employees and managers
        reportGenerator.printEmployeeReports(); // Print employee reports
        reportGenerator.printManagerReports();   // Print manager reports

        // Create an instance of BonusCalculator for the manager and print their bonus strategy
        BonusCalculator bonusCalculator = new BonusCalculator(manager1); // Pass manager1 directly
        bonusCalculator.printBonusStrategy(); // Print the bonus strategy for manager1
    }
}
