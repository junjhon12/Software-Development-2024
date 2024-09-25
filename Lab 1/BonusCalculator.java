// Class to calculate bonuses for different types of persons (Managers or Employees)
public class BonusCalculator {
    private Person person; // The person for whom we are calculating the bonus

    // Constructor to initialize the BonusCalculator with a Person
    public BonusCalculator(Person person) {
        this.person = person; // Assign the provided person to the instance variable
    }

    // Method to print the bonus strategy based on the type of person
    public void printBonusStrategy() {
        // Check if the person is a Manager
        if (person instanceof Manager) {
            // Create a PerformanceBonusStrategy for the Manager
            PerformanceBonusStrategy bonusStrategy = new PerformanceBonusStrategy((Manager) person);
            // Print the Manager's name and calculated bonus
            System.out.println("Manager: " + person.getName() + " is given a bonus of " 
                + String.format("$%,.2f", bonusStrategy.calculateBonus()));
        } 
        // Check if the person is an Employee
        else if (person instanceof Employee) {
            // Create a ProjectBonusStrategy for the Employee
            ProjectBonusStrategy bonusStrategy = new ProjectBonusStrategy((Employee) person);
            // Print the Employee's name and calculated bonus
            System.out.println("Employee: " + person.getName() + " is given a bonus of " 
                + String.format("$%,.2f", bonusStrategy.calculateBonus()));
        } 
        // If the person is neither a Manager nor an Employee
        else {
            System.out.println("Person is not eligible for a bonus."); // Notify that the person is not eligible
        }
    }
}

// Bonus Strategy Interface that defines a method to calculate bonuses
interface BonusStrategy {
    double calculateBonus(); // Method to calculate the bonus
}

// Class for calculating performance-based bonuses for Managers
class PerformanceBonusStrategy implements BonusStrategy {
    private Manager manager; // The Manager for whom we are calculating the bonus

    // Constructor to initialize with a Manager
    public PerformanceBonusStrategy(Manager manager) {
        this.manager = manager; // Assign the provided manager to the instance variable
    }

    // Method to calculate the bonus based on the Manager's bonus
    @Override
    public double calculateBonus() {
        return manager.getBonus(); // Return the Manager's bonus
    }
}

// Class for calculating project-based bonuses for Employees
class ProjectBonusStrategy implements BonusStrategy {
    private Employee employee; // The Employee for whom we are calculating the bonus

    // Constructor to initialize with an Employee
    public ProjectBonusStrategy(Employee employee) {
        this.employee = employee; // Assign the provided employee to the instance variable
    }

    // Method to calculate the bonus based on the Employee's salary
    @Override
    public double calculateBonus() {
        // Example logic for project-based bonus: 10% of the Employee's salary
        return employee.getSalary() * 0.10; // Calculate 10% of the salary as a bonus
    }
}
