// Interface defining a promotable entity
public interface Promotable {
    // Method to promote an employee
    void promote(); // The implementing class must provide the logic for promoting
}

// Class to handle the promotion of an Employee
class EmployeePromotion implements Promotable {
    private Employee employee; // The employee to be promoted

    // Constructor to initialize the EmployeePromotion with a specific Employee
    public EmployeePromotion(Employee employee) {
        this.employee = employee; // Store the reference to the Employee
    }

    // Implementation of the promote method from the Promotable interface
    @Override
    public void promote() {
        // Print the employee's name being promoted
        System.out.println("Promoting employee: " + employee.getName());
        // Additional promotion logic can be added here
    }
}
