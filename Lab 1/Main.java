import java.util.ArrayList; // Import ArrayList class
import java.util.List; // Import List interface

public class Main {
    // Entry point of the application
    public static void main(String[] args) {
        // Create lists to hold employees and managers
        List<Employee> employeeList = new ArrayList<>(); // List for Employee objects
        List<Manager> managerList = new ArrayList<>(); // List for Manager objects

        // Create instances of Employee and Manager with the Role enum
        Employee employee1 = new Employee("John Doe", 30, "123 Maple Street", "E12345", "Engineering", 85000.00,
                Role.ENGINEER);
        Manager manager1 = new Manager("Alice Johnson", 40, "789 Birch Lane", "M67890", "IT", 95000.00, 5, 10000.00,
                Role.MANAGER);
        Employee newEmployee1 = new Employee("New Guy", 21, "Some Place", "E00000", "Engineering", 0, Role.ENGINEER);

        // Add the created employee and manager to their respective lists
        employeeList.add(employee1); // Add employee1 to employeeList
        employeeList.add(newEmployee1);
        managerList.add(manager1); // Add manager1 to managerList

        // Print information for the employee and manager
        employee1.printInfo(); // Call printInfo method for employee1
        System.out.println(); // Print a blank line for spacing
        manager1.printInfo(); // Call printInfo method for manager1
        System.out.println();
        newEmployee1.printInfo();
        System.out.println();

        // Create an instance of ReportGenerator to generate reports
        ReportGenerator reportGenerator = new ReportGenerator(employeeList, managerList);
        // Print reports for all employees and managers
        reportGenerator.printEmployeeReports(); // Print employee reports
        reportGenerator.printManagerReports(); // Print manager reports

        // Create an instance of BonusCalculator for the manager and print their bonus
        // strategy
        BonusCalculator bonusCalculator = new BonusCalculator(manager1); // Pass manager1 directly
        bonusCalculator.printBonusStrategy(); // Print the bonus strategy for manager1

        // Observer pattern setup
        ObservableEmployeeList observableEmployeeList = new ObservableEmployeeList();
        observableEmployeeList.addObserver(new HRDepartmentObserver()); // Adding HR observer
        observableEmployeeList.addObserver(new ManagerObserver()); // Adding Manager observer

        // Add employee to observable list and trigger notification to observers
        System.out.println("\nAdding new employee...");
        observableEmployeeList.addEmployee(newEmployee1); // Notifies observers

        // Update employee role and department, triggering observer notifications
        System.out.println("\nUpdating employee role and department...");
        observableEmployeeList.updateEmployee(employee1, Role.ENGINEER, "Product Development");

        // Adapter pattern for external salary system
        ExternalSalaryCalculator externalCalculator = new ExternalSalaryCalculator();
        ExternalSalaryAdapter salaryAdapter = new ExternalSalaryAdapter(externalCalculator, employee1.getSalary(),
                5000.00);

        // Print total compensation using the adapter pattern
        System.out.println("\nTotal Compensation (with Adapter): " + salaryAdapter.calculateTotalCompensation());

        // Company Facade setup and usage
        CompanyFacade company = new CompanyFacade(observableEmployeeList, reportGenerator, bonusCalculator);

        // Add new employee through facade
        System.out.println("\nAdding new employee via CompanyFacade...");
        company.addEmployee(newEmployee1); // This adds employee and notifies observers
        company.printBonus(); // Prints the bonus details
        System.out.println();
        company.generateReport(); // Generates and prints reports
    }
}
