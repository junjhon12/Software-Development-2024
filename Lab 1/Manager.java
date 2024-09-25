// Class representing a Manager, which extends the Employee class
public class Manager extends Employee {
    private int teamSize;    // Number of team members managed
    private double bonus;    // Bonus amount for the manager

    // Constructor to initialize the Manager's details
    public Manager(String name, int age, String address, String employeeID, String department, double salary, int teamSize, double bonus, Role role) {
        super(name, age, address, employeeID, department, salary, role); // Call the constructor of the Employee class with provided role
        this.teamSize = teamSize;  // Initialize team size
        this.bonus = bonus;        // Initialize bonus
    }

    // Method to print the manager's information
    @Override
    public void printInfo() {
        super.printInfo(); // Call the printInfo method from the Employee class
        System.out.println("Manager Information:"); // Print header for manager information
        System.out.println("Team Size: " + teamSize); // Print the size of the team
        System.out.printf("Bonus: $%,.2f%n", bonus); // Print the bonus formatted to 2 decimal places
        System.out.printf("Total Compensation: $%,.2f%n", calculateTotalCompensation()); // Print total compensation
    }

    // Method to calculate the total compensation (salary + bonus)
    public double calculateTotalCompensation() {
        return getSalary() + bonus; // Add salary (using getter) to the bonus
    }

    // Getter method to retrieve the bonus
    public double getBonus() {
        return bonus; // Return the bonus amount
    }
}
