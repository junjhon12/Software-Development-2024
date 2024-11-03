import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void applySalaryIncrease(double percentage, double minSalary, double maxSalary) {
        List<Employee> employees = employeeDAO.getEmployeesInSalaryRange(minSalary, maxSalary);
        for (Employee emp : employees) {
            double newSalary = emp.getSalary() * (1 + percentage / 100);
            emp.setSalary(newSalary);
            employeeDAO.updateEmployee(emp);
        }
    }
}
