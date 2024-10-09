import java.util.ArrayList;
import java.util.List;

public class ObservableEmployeeList {
    private List<Employee> employees = new ArrayList<>();  // List to hold employees
    private List<Observer> observers = new ArrayList<>();  // List to hold observers

    // Add observer to the list
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove observer from the list
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Add an employee and notify observers
    public void addEmployee(Employee employee) {
        employees.add(employee);
        notifyObservers("New employee added: " + employee.getName());
    }

    // Update employee's role or department and notify observers
    public void updateEmployee(Employee employee, String newRole, String newDepartment) {
        employee.setRole(Role.valueOf(newRole));
        employee.setDepartment(newDepartment);
        notifyObservers("Employee updated: " + employee.getName() 
                        + ", New Role: " + newRole + ", New Department: " + newDepartment);
    }

    // Notify all observers
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
