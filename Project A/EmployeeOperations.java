import java.util.List;

public interface EmployeeOperations {
    void addEmployee(Employee newEmployee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int empId);
    Employee searchEmployeeById(int empId);
    List<Employee> searchEmployeeByName(String name);
    List<Employee> getEmployeesInSalaryRange(double minSalary, double maxSalary);
}
