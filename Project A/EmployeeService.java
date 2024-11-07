import java.util.List;
import java.util.function.Consumer;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void applySalaryIncrease(double percentage, double minSalary, double maxSalary) {
        List<Employee> employees = employeeDAO.getEmployeesInSalaryRange(minSalary, maxSalary);

        // Using a lambda expression to process each employee
        employees.forEach(emp -> {
            double newSalary = emp.getSalary() * (1 + percentage / 100);
            emp.setSalary(newSalary);
            employeeDAO.updateEmployee(emp); // Updating employee salary in the database
        });
    }
}
