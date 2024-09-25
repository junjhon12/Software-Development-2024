// Factory class for creating Employee and Manager objects
public class EmployeeFactory {
    // Method to create an Employee instance
    public static Employee createEmployee(String name, int age, String address, String employeeID, String department, double salary, Role role) {
        // Create and return a new Employee using the provided details
        return new Employee(name, age, address, employeeID, department, salary, role);
    }

    // Method to create a Manager instance
    public static Manager createManager(String name, int age, String address, String employeeID, String department, double salary, int teamSize, double bonus, Role role) {
        // Create and return a new Manager using the provided details
        return new Manager(name, age, address, employeeID, department, salary, teamSize, bonus, role);
    }
}
