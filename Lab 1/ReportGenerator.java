import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {
    private List<Employee> employees;
    private List<Manager> managers;
    private List<String> notifications = new ArrayList<>();

    public ReportGenerator(List<Employee> employeeList, List<Manager> managerList) {
        this.employees = employeeList;
        this.managers = managerList;
    }

    public void printEmployeeReports() {
        for (Employee employee : employees) {
            printEmployeeReport(employee);
        }
    }

    public void printManagerReports() {
        for (Manager manager : managers) {
            printManagerReport(manager);
        }
    }

    public void trackNotification(String message) {
        notifications.add(message);
    }

    private void printEmployeeReport(Employee employee) { 
        System.out.printf("Employee Report: %s, Salary: $%,.2f, Role: %s%n", 
                          employee.getName(), employee.getSalary(), employee.getRole());
    }

    private void printManagerReport(Manager manager) {  
        System.out.printf("Manager Report: %s, Salary with bonus: $%,.2f%n", 
                          manager.getName(), manager.calculateTotalCompensation());
    }

    public void generateChangeReport() {
        System.out.println("Employee Changes Report:");
        for (String notification : notifications) {
            System.out.println(notification);
        }
    }
}
