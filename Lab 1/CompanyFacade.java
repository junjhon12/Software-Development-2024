public class CompanyFacade {
    private ObservableEmployeeList employeeList;
    private ReportGenerator reportGenerator;
    private BonusCalculator bonusCalculator;

    public CompanyFacade(ObservableEmployeeList employeeList, ReportGenerator reportGenerator, BonusCalculator bonusCalculator) {
        this.employeeList = employeeList;
        this.reportGenerator = reportGenerator;
        this.bonusCalculator = bonusCalculator;
    }

    public void addEmployee(Employee employee) {
        employeeList.addEmployee(employee);
        reportGenerator.trackNotification("Employee added: " + employee.getName());
    }

    public void printBonus() {
        bonusCalculator.printBonusStrategy();
    }

    public void generateReport() {
        reportGenerator.generateChangeReport();
    }
}
