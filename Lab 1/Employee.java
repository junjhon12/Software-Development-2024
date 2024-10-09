// Class representing an Employee, which extends the Person class
public class Employee extends Person {
    private String employeeID;   // Unique identifier for the employee
    private String department;    // Department where the employee works
    protected double salary;       // Employee's salary (protected for subclass access)
    private Role role;            // Role of the employee (added field)

    // Constructor to initialize the Employee's details
    public Employee(String name, int age, String address, String employeeID, String department, double salary, Role role) {
        super(name, age, address); // Call the constructor of the Person class
        this.employeeID = employeeID; // Initialize the employee ID
        this.department = department;   // Initialize the department
        this.salary = salary;           // Initialize the salary
        this.role = role;               // Initialize the role
    }

    // Method to print the employee's information
    @Override
    public void printInfo() {
        super.printInfo(); // Call the printInfo method from the Person class
        System.out.println("Employee Information:"); // Print header for employee information
        System.out.println("Employee ID: " + employeeID); // Print the employee ID
        System.out.println("Department: " + department); // Print the department
        System.out.printf("Salary: $%,.2f%n", salary); // Print the salary formatted to 2 decimal places
        System.out.println("Role: " + role); // Print the employee's role
    }

    // Getter method to retrieve the employee ID
    public String getEmployeeID() {
        return employeeID; // Return the employee ID
    }

    // Getter method to retrieve the department
    public String getDepartment() {
        return department; // Return the department
    }

    // Getter method to retrieve the salary
    public double getSalary() {
        return salary; // Return the salary
    }

    // Getter method to retrieve the role
    public Role getRole() {
        return role; // Return the role
    }

    public void setRole(Role valueOf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRole'");
    }

    public void setDepartment(String newDepartment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDepartment'");
    }
}
