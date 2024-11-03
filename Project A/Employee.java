public class Employee {
    private int empId;
    private String name;
    private String ssn;
    private String jobTitle;
    private String division;
    private double salary;

    // Constructor
    public Employee(int empId, String name, String ssn, String jobTitle, String division, double salary) {
        this.empId = empId;
        this.name = name;
        this.ssn = ssn;
        this.jobTitle = jobTitle;
        this.division = division;
        this.salary = salary;
    }

    // Getters
    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDivision() {
        return division;
    }

    public double getSalary() {
        return salary;
    }

    // Setters
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", division='" + division + '\'' +
                ", salary=" + salary +
                '}';
    }
}
